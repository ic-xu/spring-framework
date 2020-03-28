package com.mapper;

import com.entity.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

	@Select("SELECT * FROM dept")
	List<Dept> getUser();
}
