package com.macro.mall.service.impl.adminServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.admin.XbzAdminPermissionRelationDao;
import com.macro.mall.dao.admin.XbzAdminRoleRelationDao;
import com.macro.mall.dto.admin.XbzAdminParam;
import com.macro.mall.mapper.XbzAdminLoginLogMapper;
import com.macro.mall.mapper.XbzAdminMapper;
import com.macro.mall.mapper.XbzAdminPermissionRelationMapper;
import com.macro.mall.mapper.XbzAdminRoleRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.AdminService.XbzAdminService;
import com.macro.mall.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * XbzAdminService实现类
 * Created by macro on 2018/4/26.
 */
@Service
public class XbzAdminServiceImpl implements XbzAdminService {
    private static final Logger LOGGER = LoggerFactory.getLogger(XbzAdminServiceImpl.class);
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private XbzAdminMapper adminMapper;
    @Resource
    private XbzAdminRoleRelationMapper adminRoleRelationMapper;
    @Resource
    private XbzAdminRoleRelationDao adminRoleRelationDao;
    @Resource
    private XbzAdminPermissionRelationMapper adminPermissionRelationMapper;
    @Resource
    private XbzAdminPermissionRelationDao adminPermissionRelationDao;
    @Resource
    private XbzAdminLoginLogMapper loginLogMapper;

    @Override
    public XbzAdmin getAdminByUsername(String username) {
        XbzAdminExample example = new XbzAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<XbzAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            return adminList.get(0);
        }
        return null;
    }

    @Override
    public XbzAdmin register(XbzAdminParam XbzAdminParam) {
        XbzAdmin XbzAdmin = new XbzAdmin();
        BeanUtils.copyProperties(XbzAdminParam, XbzAdmin);
        XbzAdmin.setCreateTime(new Date());
        XbzAdmin.setStatus(1);
        //查询是否有相同用户名的用户
        XbzAdminExample example = new XbzAdminExample();
        example.createCriteria().andUsernameEqualTo(XbzAdmin.getUsername());
        List<XbzAdmin> XbzAdminList = adminMapper.selectByExample(example);
        if (XbzAdminList.size() > 0) {
            return null;
        }
        //将密码进行加密操作
        String md5Password = passwordEncoder.encode(XbzAdmin.getPassword());
        XbzAdmin.setPassword(md5Password);
        adminMapper.insert(XbzAdmin);
        return XbzAdmin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            updateLoginTimeByUsername(username);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        XbzAdmin admin = getAdminByUsername(username);
        XbzAdminLoginLog loginLog = new XbzAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
    }

    /**
     * 根据用户名修改登录时间
     */
    private void updateLoginTimeByUsername(String username) {
        XbzAdmin record = new XbzAdmin();
        record.setLoginTime(new Date());
        XbzAdminExample example = new XbzAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        adminMapper.updateByExampleSelective(record, example);
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring(tokenHead.length());
        if (jwtTokenUtil.canRefresh(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public XbzAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<XbzAdmin> list(String name, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XbzAdminExample example = new XbzAdminExample();
        XbzAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andUsernameLike("%" + name + "%");
            example.or(example.createCriteria().andNickNameLike("%" + name + "%"));
        }
        return adminMapper.selectByExample(example);
    }

    @Override
    public int update(Long id, XbzAdmin admin) {
        admin.setId(id);
        return adminMapper.updateByPrimaryKey(admin);
    }

    @Override
    public int delete(Long id) {
        return adminMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        XbzAdminRoleRelationExample adminRoleRelationExample = new XbzAdminRoleRelationExample();
        adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<XbzAdminRoleRelation> list = new ArrayList<>();
            for (Long roleId : roleIds) {
                XbzAdminRoleRelation roleRelation = new XbzAdminRoleRelation();
                roleRelation.setAdminId(adminId);
                roleRelation.setRoleId(roleId);
                list.add(roleRelation);
            }
            adminRoleRelationDao.insertList(list);
        }
        return count;
    }

    @Override
    public List<XbzRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permissionIds) {
        //删除原所有权限关系
        XbzAdminPermissionRelationExample relationExample = new XbzAdminPermissionRelationExample();
        relationExample.createCriteria().andAdminIdEqualTo(adminId);
        adminPermissionRelationMapper.deleteByExample(relationExample);
        //获取用户所有角色权限
        List<XbzPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        List<Long> rolePermissionList = permissionList.stream().map(XbzPermission::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permissionIds)) {
            List<XbzAdminPermissionRelation> relationList = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permissionIds.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出-权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId -> !permissionIds.contains(permissionId)).collect(Collectors.toList());
            //插入+-权限关系
            relationList.addAll(convert(adminId,1,addPermissionIdList));
            relationList.addAll(convert(adminId,-1,subPermissionIdList));
            return adminPermissionRelationDao.insertList(relationList);
        }
        return 0;
    }

    /**
     * 将+-权限关系转化为对象
     */
    private List<XbzAdminPermissionRelation> convert(Long adminId, Integer type, List<Long> permissionIdList) {
        List<XbzAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
            XbzAdminPermissionRelation relation = new XbzAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }

    @Override
    public List<XbzPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getPermissionList(adminId);
    }
}
