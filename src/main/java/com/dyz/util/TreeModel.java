package com.dyz.util;

import java.util.ArrayList;

public class TreeModel {
	private int id;                    //id
    private String text;                //标题内容
    private String state;               //状态 closed-合起、open-打开
    private Boolean  checked	;		//1:被选中的；0：未被选中的
    private String modules_path;               //路径
    private ArrayList<TreeModel> children;   //子节点，子节点有0个或多个，所以用链表存放
    private int level_id;               //层级       根层级为1
    private int parent_id;              //父节点   根节点为0
    
     
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public String getModules_path() {
		return modules_path;
	}
	public void setModules_path(String modules_path) {
		this.modules_path = modules_path;
	}
	public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public ArrayList<TreeModel> getChildren() {
        return children;
    }
    public void setChildren(ArrayList<TreeModel> children) {
        this.children = children;
    }
    public int getLevel_id() {
        return level_id;
    }
    public void setLevel_id(int level_id) {
        this.level_id = level_id;
    }
    public int getParent_id() {
        return parent_id;
    }
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

}
