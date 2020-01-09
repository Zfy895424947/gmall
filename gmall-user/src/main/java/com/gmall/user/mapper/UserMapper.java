package com.gmall.user.mapper;

import com.gmall.user.bean.UmsMember;
import java.util.List;

public interface UserMapper {
    List<UmsMember> selectAllUser();
}
