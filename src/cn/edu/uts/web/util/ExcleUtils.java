package cn.edu.uts.web.util;

import java.io.OutputStream;
import java.util.List;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import cn.edu.uts.web.domain.AddRecord;
import cn.edu.uts.web.domain.Sell;

public class ExcleUtils {
   public static void createSellExcle(OutputStream os,String begin,String end,List<Sell> sells){
		try {
			//设置输入的格式
			WritableFont font1= new WritableFont(WritableFont.TIMES,10,WritableFont.BOLD);
			WritableCellFormat format1=new WritableCellFormat(font1);
			//标题的格式
			WritableFont font2= new WritableFont(WritableFont.TIMES,20,WritableFont.BOLD);
			WritableCellFormat format2=new WritableCellFormat(font2);
			format2.setAlignment(jxl.format.Alignment.CENTRE); // 水平设置对齐方式  
			format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//垂直对齐
			// 创建工作簿
			WritableWorkbook book = Workbook.createWorkbook(os);
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("销售明细", 0);
			//设置名字
			Label title = new Label(0, 0, "销售明细",format2);
			sheet.addCell(title);
			//起始点点是第1列第1行，终止点是第8列第3行
		    sheet.mergeCells(0, 0, 7, 2);
		    Label date = new Label(0, 3, "时间:"+begin+"到"+end+"");
			sheet.addCell(date);
			//起始点是第1列第4行，终止点是第8列第4行
			sheet.mergeCells(0,3,7,3);
			Label label = new Label(0, 4, "销售时间",format1);
			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);
		    Label label1 = new Label(1, 4, "面包名称",format1);
			sheet.addCell(label1);
			Label label2 = new Label(2, 4, "销售数量",format1);
			sheet.addCell(label2);
        	Label label3 = new Label(3, 4, "销售价格",format1);
			sheet.addCell(label3);
			for(int line=5;line<sells.size()+5;line++){
				Label label4=new Label(0,line,sells.get(line-5).getSelldate().toString());
				sheet.addCell(label4);
				Label label5=new Label(1,line,sells.get(line-5).getBread().getName());
				sheet.addCell(label5);
				Label label6=new Label(2,line,sells.get(line-5).getSellnum()+"");
				sheet.addCell(label6);
				Label label7=new Label(3,line,sells.get(line-5).getSellprice()+"");
				sheet.addCell(label7);
			}
			// 写入数据并关闭文件
			book.write();
			book.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
public static void createAddExcle(OutputStream os, String begin, String end,
		List<AddRecord> addRecords) {
	try {
		//设置输入的格式
		WritableFont font1= new WritableFont(WritableFont.TIMES,10,WritableFont.BOLD);
		WritableCellFormat format1=new WritableCellFormat(font1);
		//标题的格式
		WritableFont font2= new WritableFont(WritableFont.TIMES,20,WritableFont.BOLD);
		WritableCellFormat format2=new WritableCellFormat(font2);
		format2.setAlignment(jxl.format.Alignment.CENTRE); // 水平设置对齐方式  
		format2.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);//垂直对齐
		// 创建工作簿
		WritableWorkbook book = Workbook.createWorkbook(os);
		// 生成名为“第一页”的工作表，参数0表示这是第一页
		WritableSheet sheet = book.createSheet("入库明细", 0);
		//设置名字
		Label title = new Label(0, 0, "入库明细",format2);
		sheet.addCell(title);
		//起始点点是第1列第1行，终止点是第8列第3行
	    sheet.mergeCells(0, 0, 7, 2);
	    Label date = new Label(0, 3, "时间:"+begin+"到"+end+"");
		sheet.addCell(date);
		//起始点是第1列第4行，终止点是第8列第4行
		sheet.mergeCells(0,3,7,3);
		Label label = new Label(0, 4, "销售时间",format1);
		// 将定义好的单元格添加到工作表中
		sheet.addCell(label);
	    Label label1 = new Label(1, 4, "面包名称",format1);
		sheet.addCell(label1);
		Label label2 = new Label(2, 4, "入库数量",format1);
		sheet.addCell(label2);
    	Label label3 = new Label(3, 4, "入库价格",format1);
		sheet.addCell(label3);
		for(int line=5;line<addRecords.size()+5;line++){
			Label label4=new Label(0,line,addRecords.get(line-5).getAddtime().toString());
			sheet.addCell(label4);
			Label label5=new Label(1,line,addRecords.get(line-5).getBread().getName());
			sheet.addCell(label5);
			Label label6=new Label(2,line,addRecords.get(line-5).getNum()+"");
			sheet.addCell(label6);
			Label label7=new Label(3,line,addRecords.get(line-5).getPrice()+"");
			sheet.addCell(label7);
		}
		// 写入数据并关闭文件
		book.write();
		book.close();
	} catch (Exception e) {
		System.out.println(e);
	}
}
   
}
