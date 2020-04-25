package com.mybites.spring.entity;


public class Dept {
	private Integer deptno;

	private String dname;


	public Integer getDeptno() {
		return deptno;
	}

	public String getDname() {
		return dname;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}


	@Override
	public String toString() {
		return "Dept{" +
				"deptno=" + deptno +
				", dname='" + dname + '\'' +
				'}';
	}
}
