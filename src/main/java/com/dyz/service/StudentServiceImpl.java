package com.dyz.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dyz.dao.MembershipsRepository;
import com.dyz.dao.StudentRepository;
import com.dyz.entity.Memberships;
import com.dyz.entity.Search;
import com.dyz.entity.Student;
import com.dyz.entity.Teacher;

@Service
public class StudentServiceImpl implements StudentService {

	
	@Autowired
	private StudentRepository repository;
	@Autowired
	private MembershipsRepository mrepository;
	@Override
	public Search selectStudent(Search search) {
		Integer total=repository.selectCount(search);
		List<Student> rows=repository.selectStudent(search);
		search.setTotal(total);
		search.setRows(rows);
		return search;
	}
	@Override
	public int updateStudent(Student stu) {
		// TODO Auto-generated method stub
		Memberships mber=mrepository.findById(1).get();
		stu.setMeber(mber);
		return repository.updateStudent(stu);
	}
	@Override
	public int addStudent(Student stu) {
		Memberships mber=mrepository.findById(1).get();
		stu.setMeber(mber);
		return repository.addStudent(stu);
	}
	
	/**
	 * 导入学生
	 */
	public Integer ImportExcelStudent(MultipartFile file) {
		
		boolean notNull=false;
		String fileName = file.getOriginalFilename();//获得原始文件名; 
		List<Student> studentList = new ArrayList<>();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				wb = new XSSFWorkbook(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Sheet sheet = wb.getSheetAt(0);
		if (sheet != null) {
			notNull = true;
		}
		Student student;
		for (int r = 1; r <= sheet.getLastRowNum(); r++) {// r = 2 表示从第三行开始循环 如果你的第三行开始是数据
			Row row = sheet.getRow(r);// 通过sheet表单对象得到 行对象
			if (row == null) {
				continue;
			}
// sheet.getLastRowNum() 的值是 10，所以Excel表中的数据至少是10条；不然报错 NullPointerException
			student = new Student();
			if (row.getCell(0).getCellType() != 1) {
				// 循环时，得到每一行的单元格进行判断
				System.out.println("导入失败(第" + (r + 1) + "行,用户名请设为文本格式)");
			}
			// 得到每一行的 第二个单元格的值
			row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);// 卡号
			row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);// 状态
			row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);// 学号
			row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);// 姓名
			row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);// 备注
			row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);// 性别
			row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);// 院系
			
			String scardNO = row.getCell(0).getStringCellValue();
			String Sstatus = row.getCell(1).getStringCellValue();
			String stuNO = row.getCell(2).getStringCellValue();
			 String studentName = row.getCell(3).getStringCellValue();
			String studentRemark = row.getCell(4).getStringCellValue();
			String studentSex = row.getCell(5).getStringCellValue();
			String MembershipId = row.getCell(6).getStringCellValue();
 			//判断性别【1：男0：女】
			if("男".equals(studentSex.trim())) {
				student.setStudentSex(1);
			}
			if("女".equals(studentSex.trim())) {
				student.setStudentSex(0);
			}
			// 完整的循环一次 就组成了一个对象
			student.setScardNO(scardNO);
			student.setSstatus(Integer.parseInt(Sstatus));
			student.setStudentName(studentName);
			student.setStudentRemark(studentRemark);
			student.setStuNO(stuNO);
			student.setMembershipId(Integer.parseInt(MembershipId));
			studentList.add(student);
 			}
 	      for (Student t : studentList) {
	         String cardNo = t.getScardNO();
	         //判断里面的数据是否有它
	         Student cnt = repository.findByscardNO(cardNo);
	         if (cnt == null) {
	        	 repository.save(t);
	            System.out.println(" 插入 "+t);
	         } else {
	            t.setStudentId(cnt.getStudentId());
	            repository.save(t);
	            System.out.println(" 更新 "+t);
	         }
	      }
		
		
		return 1;
	}

}
