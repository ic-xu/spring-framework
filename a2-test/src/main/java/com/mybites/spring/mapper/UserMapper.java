package com.mybites.spring.mapper;

import com.mybites.spring.entity.Dept;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

	@Select("SELECT * FROM dept")
	List<Dept> getUser();
}
