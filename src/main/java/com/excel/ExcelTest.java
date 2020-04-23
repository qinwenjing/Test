package com.excel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.drew.tools.FileUtil;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelTest {
    public static void main(String[] args) {
        Dept dept = new Dept();
        dept.setName("deptname");
        Role role = new Role();
        role.setName("女");
        User user = new User("qinwenjing", "qin", "notype", Arrays.asList(role), dept,  "nostatus");
        List<User> list = Arrays.asList(user);
        String sheetName = "sheet01";
        List<String> columns = Arrays.asList("username", "cnname", "userType", "性别", "状态");
        exportExcel(list, sheetName, columns);
    }
    public static int exportExcel(List<User> list, String sheetName, List<String> columns) {
        int flag = 0;
        //声明工作簿jxl.write.WritableWorkbook
        WritableWorkbook wwb;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        InputStream is = new ByteArrayInputStream(stream.toByteArray());
        try {
            //在本地创建一个excel文件，如果文件存在每次都会生成新的覆盖原来的文件，
            //这样就永远只生成一个excel，而不占用服务器太多空间
            wwb = Workbook.createWorkbook(stream);
            /*
             * 创建一个工作表、sheetName为工作表的名称、"0"为第一个工作表
             * 打开Excel的时候会看到左下角默认有3个sheet、"sheet1、sheet2、sheet3"这样
             * 代码中的"0"就是sheet1、其它的一一对应。
             * createSheet(sheetName, 0)一个是工作表的名称，另一个是工作表在工作薄中的位置
             */
            WritableSheet ws = wwb.createSheet(sheetName, 0);

            SheetSettings ss = ws.getSettings();
            ss.setVerticalFreeze(1);//冻结表头

            WritableFont font1 =new WritableFont(WritableFont.createFont("微软雅黑"), 10 ,WritableFont.BOLD);
            WritableFont font2 =new WritableFont(WritableFont.createFont("微软雅黑"), 9 ,WritableFont.NO_BOLD);
            WritableCellFormat wcf = new WritableCellFormat(font1);
            WritableCellFormat wcf2 = new WritableCellFormat(font2);
            WritableCellFormat wcf3 = new WritableCellFormat(font2);//设置样式，字体

            //创建单元格样式
            //WritableCellFormat wcf = new WritableCellFormat();

            //背景颜色
            wcf.setBackground(jxl.format.Colour.YELLOW);
            wcf.setAlignment(Alignment.CENTRE);  //平行居中
            wcf.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中
            wcf3.setBackground(Colour.LIGHT_ORANGE);
            wcf3.setAlignment(Alignment.CENTRE);  //平行居中
            wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中

            wcf2.setAlignment(Alignment.CENTRE);  //平行居中
            wcf2.setVerticalAlignment(VerticalAlignment.CENTRE);  //垂直居中

            /*
             * 这个是单元格内容居中显示
             * 还有很多很多样式
             */
            wcf.setAlignment(Alignment.CENTRE);

            //判断一下表头数组是否有数据
            if (columns != null && columns.size() > 0) {

                //循环写入表头
                for (int i = 0; i < columns.size(); i++) {

                    /*
                     * 添加单元格(Cell)内容addCell()
                     * 添加Label对象Label()
                     * 数据的类型有很多种、在这里你需要什么类型就导入什么类型
                     * 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label
                     * Label(i, 0, columns[i], wcf)
                     * 其中i为列、0为行、columns[i]为数据、wcf为样式
                     * 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中
                     */
                    ws.addCell(new Label(i, 0, columns.get(i), wcf));
                }

                //判断表中是否有数据
                if (list != null && list.size() > 0) {
                    //循环写入表中数据
                    int i=0;
                    for(User user:list){
                        //括号中第一个参数代表列，第二个参数代表行，第三个参数代表内容
                        //下面第一个也就是第0列的第1行（第0行做了表头）的内容为userName。
                        ws.addCell(new Label(0,i+1,user.getUserName()));
                        ws.addCell(new Label(1,i+1,user.getCnName()));
                        ws.addCell(new Label(2,i+1,user.getUserType()));
                        if(user.getRoleList()==null){
                            ws.addCell(new Label(3,i+1,""));
                        }else{
                            ws.addCell(new Label(3,i+1,user.getRoleList().get(0).getName()));
                        }
                        if(user.getDept()==null){
                            ws.addCell(new Label(4,i+1,""));
                        }else{
                            ws.addCell(new Label(4,i+1,user.getDept().getName()));
                        }
                        if(user.getDept()==null){
                            ws.addCell(new Label(5,i+1,""));
                        }else{
                            ws.addCell(new Label(5,i+1,user.getDept().getName()));
                        }
                        ws.addCell(new Label(6,i+1,user.getStatus()));
                        i++;
                    }

                }else{
                    flag = -1;
                }

                //写入Exel工作表
                wwb.write();
                //关闭Excel工作薄对象
                wwb.close();
                is = new ByteArrayInputStream(stream.toByteArray());

                String fileName=new String("用户信息.xls".getBytes("GBK"),"ISO8859_1");
               // Filedownload.save(is, "application/x-download", fileName);
            }
        }catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        catch (Exception ex) {
            flag = 0;
            ex.printStackTrace();
        }

        return flag;
    }

}
