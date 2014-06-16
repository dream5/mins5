package com.mins5.pageUI.entity;

public class TreeData {
	private String id;
	private String name;
	private String pid;
	private String parentis;
	private String nocheck;
	private String checked;
	private String open;

	public TreeData() {
		super();
	}

	public TreeData(String id, String name, String pid, String parentis,
			String nocheck, String checked, String open) {
		super();
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.parentis = parentis;
		this.nocheck = nocheck;
		this.checked = checked;
		this.open = open;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getParentis() {
		return parentis;
	}

	public void setParentis(String parentis) {
		this.parentis = parentis;
	}

	public String getNocheck() {
		return nocheck;
	}

	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

}