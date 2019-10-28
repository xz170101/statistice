package com.dyz.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dyz.dao.TeacherRepository;
import com.dyz.entity.Section;
import com.dyz.entity.Teacher;
import com.dyz.util.FileConvertUtil;
import com.dyz.util.ImportExcelUtil;

@Service
public class ImportExcelTeacherServiceImpl implements ImportExcelTeacherService{
	
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private ImportExcelUtil importExcelUtil;
	
	@Override
	public Integer ImportExcelTeacher(MultipartFile file) {
  		//获取传过来的文件名 ：String fileName=file.getOriginalFilename();
		boolean notNull=false;
		String fileName = file.getOriginalFilename();//获得原始文件名; 
		List<Teacher> teacherList = new ArrayList<>();
		boolean isExcel2003 = true;
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}
		InputStream is = null;
		try {
			is = file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook wb = null;
		if (isExcel2003) {
			try {
				wb = new HSSFWorkbook(is);
			} catch (IOException e) {
 				e.printStackTrace();
			}
		} else {
			try {
				wb = new XSSFWorkbook(is);
			} catch (IOException e) {
 				e.printStackTrace();
			}
		}
		Sheet sheet = wb.getSheetAt(0);
		if (sheet != null) {
			notNull = true;
		}
		Teacher teachers;
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {// r = 2 表示从第三行开始循环 如果你的第三行开始是数据
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
// sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
			teachers = new Teacher();
			if (row.getCell(0).getCellType() != 1) {
				// 循环时，得到每一行的单元格进行判断
				System.out.println("导入失败(第" + (r + 1) + "行,用户名请设为文本格式)");
			}
			// 得到每一行的 第二个单元格的值
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);// 卡号
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);// 名字
			row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);// 性别
			row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);// 阅览室
			row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);// 状态
			row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);// 备注
			
			String cardNo = row.getCell(0).getStringCellValue();
			String teacherName = row.getCell(1).getStringCellValue();
			String teacherSex = row.getCell(2).getStringCellValue();
			String sectionId = row.getCell(3).getStringCellValue();
			String status = row.getCell(4).getStringCellValue();
			String remark = row.getCell(5).getStringCellValue();
			//判断性别【1：男0：女】
			if("男".equals(teacherSex)) {
				teachers.setTeacherSex(1);
			}else if("女".equals(teacherSex)) {
				teachers.setTeacherSex(0);
			}
			// 完整的循环一次 就组成了一个对象
			teachers.setTcardno(cardNo);
			teachers.setTeacherName(teacherName);
			teachers.setSectionId(Integer.parseInt(sectionId));
			teachers.setTstatus(Integer.parseInt(status));
			teachers.setRemark(remark);
			teacherList.add(teachers);
 			}
	      for (Teacher t : teacherList) {
	         String cardNo = t.getTcardno();
	         //判断里面的数据是否有它
	         Teacher cnt = teacherRepository.findBytcardno(cardNo);
	         if (cnt == null) {
	        	 teacherRepository.save(t);
	            System.out.println(" 插入 "+t);
	         } else {
	            t.setTeacherId(cnt.getTeacherId());
	            teacherRepository.save(t);
	            System.out.println(1);
	            System.out.println(" 更新 "+t);
	         }
	         
	      }
		/*int pointIndex =  fileRealName.lastIndexOf(".");//点号的位置     
		String fileSuffix = fileRealName.substring(pointIndex);//截取文件后缀  
		String fileNewName = DateUtils.getNowTimeForUpload();//新文件名,时间戳形式yyyyMMddHHmmssSSS
		String saveFileName = fileNewName.concat(fileSuffix);//新文件完整名（含后缀） 
		 */ 
        //将上传到服务器上的二进制文件写成实际文件
 		//根据卡号判断先判断卡号类型（教师's' or 学生't'）
		//分别存到对应的集合
		//再根据卡号类型查询数据库是否有该对象 
		//存在的话就根据卡号更新这个对象
		//不存在就直接添加到对应数据表
		return 1;
	}

}
