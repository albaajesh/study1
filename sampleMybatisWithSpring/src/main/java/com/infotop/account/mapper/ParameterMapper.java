package com.infotop.account.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.infotop.account.model.Parameter;

public interface ParameterMapper {

	List<Parameter> datagridParameter(@Param("parentId") String parentId);

	Parameter getParameterById(long id);

	void saveParameter(Parameter parameter);

	void updateParameter(Parameter parameter);

	void deleteParameter(long id);

}
