package com.wp.cloud.shop.controller.user;

import com.wp.cloud.shop.dto.user.UserDto;
import com.wp.cloud.shop.fegin.user.UserFeign;
import com.wp.cloud.shop.service.user.UserService;
import com.wp.cloud.shop.vo.user.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: shop-cloud--UserController
 * @Description:
 * @Author suanmilk
 * @CreateTime: 2019-01-30 13:06
 */
@RestController
@Slf4j
@Api(description = "用户管理")
public class UserController implements UserFeign {

    @Autowired
    private UserService userService;

    @Override
    @ApiOperation(value = "查询所有用户信息", notes = "查询所有用户信息")
    public List<UserDto> getUsers(@ModelAttribute UserVo userVo) {
//        Specification specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            if (null != userVo.getVip()) {
//                predicates.add(criteriaBuilder.equal(root.get("vip"), userVo.getVip()));
//            }
//            if (null != userVo.getNamelike()) {
//                predicates.add(criteriaBuilder.like(root.get("name"), "%" + userVo.getNamelike() + "%"));
//            }
//            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//        };
//        List<User> userList = userService.findAll(specification);
//        return userList.stream().map(value -> {
//            UserDto userDto = new UserDto();
//            BeanUtils.copyProperties(value, userDto);
//            return userDto;
//        }).collect(Collectors.toList());
        return null;
    }

    @Override
    @ApiOperation(value = "根据ID查询用户信息", notes = "根据ID查询用户信息")
    public UserDto getUser(@PathVariable @ApiParam(value = "用户ID", required = true, example = "1") Long id) {
//        Optional<User> user = userService.findById(id);
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setAge(18);
        userDto.setName("张三");
        userDto.setVip(true);
        if(id > 1000){
            try {
                Thread.sleep(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        if (user.isPresent()) {
//            userDto = new UserDto();
//            BeanUtils.copyProperties(user.get(), userDto);
//        }
        return userDto;
    }
}
