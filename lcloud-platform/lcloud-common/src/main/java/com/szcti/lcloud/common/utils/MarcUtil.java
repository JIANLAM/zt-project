package com.szcti.lcloud.common.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 * 处理书目marc解析的工具类
 * @author fd
 */
public class MarcUtil {

	private static String code = "";

	private static final String BREAK_LINE = "-------\n";

	public static void main(String[] args) throws Exception {
		String file1 = "C:/Users/Administrator/Desktop/aaa.ISO";
		getInfoByISO(file1);

		/*Book book = new Book();
		book.setCreateTime("2017-10-17 07:39:44");
		book.setIsbn("978-7-5002-5763-9");
		book.setPrice(new Float(15.00));
		book.setLanguage("中文");
		book.setTitle("西游记");
		book.setSecondTitle("西行");
		book.setAuthor("吴承恩");
		book.setRevision("第二版");
		book.setPubArea("北京");
		book.setPublisher("北京出版社");
		book.setPubdate("2011-11-05 07:39:44");
		book.setPages(369);
		book.setSummary("这是简介。");
		book.setSubjectWord("中国名著");
		book.setBookType("K928.75");
		System.out.println(obj2Row(book));
		System.out.println(obj2Rows(book));


		List list = new ArrayList();
		list.add(book);
		list.add(book);
		objs2ISOStr(list);*/

	}

	/**
	 * 将ISO文件解析为t_book表格式的数据
	 * @param file	ISO文件路径
	 * @return
	 */
	public static List<Map> getInfoByISO(@NonNull String file){
		List<Map> list = new ArrayList<>();
		String rows = readFileByISO(file);
		if(StringUtils.isNotEmpty(rows)){
			list = rows2Obj(rows);
		}
		System.out.println("解析后的数据为："+list);
		return list;
	}

	/**
	 * 根据一行code获取其字段信息
	 * @param row
	 * @return
	 */
	private static Map<String,Object> getInfoByRow(String row){
		Map<String,Object> map = new HashMap<>(16);
		//一行数据的前三个字符为字段标识
		String key = row.substring(0,3);
		//定义一行数据中标识主要信息的字符段
		String infoCode;
		//001为记录控制号，暂不需要输出到对象
		if("001".equals(key)){
			infoCode = row.substring(3);
			System.out.println("记录控制号:"+infoCode);
		}
		//005为记录版次标识，暂不需要输出对象
		if("005".equals(key)){
			infoCode = row.substring(3);
			System.out.println("记录版次标识:"+infoCode);
		}
		//010为国际标准书号，即ISBN编号
		if("010".equals(key)){
			String []info = row.split("\\$");
			if(info[1].contains("a")){
				String ISBN = info[1].replace("a","");
				map.put("ISBN",ISBN);
			}
			if(info[info.length-1].contains("d")){
				String price = info[info.length-1].replace("d","");
				map.put("price",price);
			}
		}
		//100为一般数据处理（如入档日期、出版日期、阅读对象代码等），暂不需要输出对象
		if("100".equals(key)){
			String []info = row.split("\\$");
			String []date = info[1].split(" ");
			if(date[0].contains("a")&&date[0].contains("d")){
				String []a = date[0].split("d");
				String inDate = a[0].replace("a","");
				String pubDate = a[1];
				map.put("pubDate",pubDate);
			}
		}
		//101为作品语种
		if("101".equals(key)){
			String []info = row.split("\\$");
			if(info[1].contains("a")){
				String language = info[1].replace("a","");
				if("chi".equals(language)){
					language = "中文";
				}
				if("eng".equals(language)){
					language = "英文";
				}
				map.put("language",language);
			}
		}
		//200为题名及责任说明项
		if("200".equals(key)){
			String []info = row.split("\\$");
			if(info[1].contains("a")){
				String title = info[1].replace("a","");
				map.put("title",title);
			}
		}
		//205为版次信息
		if("205".equals(key)){
			String []info = row.split("\\$");
			if(info[1].contains("a")){
				String revision = info[1].replace("a","");
				map.put("revision",revision);
			}
		}
		//210为出版发行项
		if("210".equals(key)){
			String []info = row.split("\\$");
			for(int i = 0;i < info.length;i ++){
				if(info[i].contains("a")){
					String pubArea = info[i].replace("a","");
					map.put("pubArea",pubArea);
				}
				if(info[i].contains("c")){
					String publisher = info[i].replace("c","");
					map.put("publisher",publisher);
				}
				if(info[i].contains("d")){
					String pubDate = info[i].replace("d","");
					map.put("pubDate",pubDate);
				}
			}
		}
		//215为载体形态项，如页数
		if("215".equals(key)){
			String []info = row.split("\\$");
			String pages = "";
			if(info[1].contains("a")){
				pages = info[1].replace("a","");
				if(pages.contains(",")){
					String []pageInfo = pages.split(",");
					pages = pageInfo[1];
				}
				pages = pages.replace("页","");
				map.put("pages",pages);
			}
		}
		//330为提要或文摘
		if("330".equals(key)){
			String []info = row.split("\\$a");
			map.put("summary",info[1]);
		}
		//690为中国图书馆分类法分类号
		if("690".equals(key)){
			String []info = row.split("\\$");
			if(info[1].contains("a")){
				String bookType = info[1].replace("a","");
				map.put("bookType",bookType);
			}
		}
		//606为主题词
		if("606".equals(key)){
			String []info = row.split("\\$");
			if(info[1].contains("a")){
				String subjectWord = info[1].replace("a","");
				map.put("subjectWord",subjectWord);
			}
		}
		//701为个人名称-主要责任人（作者）
		if("701".equals(key)){
			String []info = row.split("\\$");
			if(info[1].contains("a")){
				String author = info[1].replace("a","");
				map.put("author",author);
				map.put("firstDuty",author);
			}
		}


		return map;
	}

	/**
	 * 将多行code数据转换为图书数据对象(只支持用BREAK_LINE分割的数据)
	 * @param codeRows 多行code数据
	 * @return List
	 */
	private static List<Map> rows2Obj(@NonNull String codeRows){
		List<Map> list = new ArrayList<>();
		String []code = codeRows.split(BREAK_LINE);
		if(code.length<=0){
			return null;
		}
		for(int i = 0;i<code.length;i++){
			Map<String,Object> map = new HashMap<>(16);
			String []rows = code[i].split("\n");
			for(int j = 0;j<rows.length;j++){
				Map<String,Object> row = getInfoByRow(rows[j]);
				if(row!=null){
					map.putAll(row);
				}
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 将ISO文件读取为多行code数据
	 * @param fileName ISO文件路径
	 * @return String
	 */
	public static String readFileByISO(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		code = "";
		try {
			//	System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"GBK"));

			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				try{
					if(StringUtils.isNotEmpty(tempString)){
						code += "LDR "+tempString.substring(0,24)+"\n";
						showMarc(tempString.getBytes("GBK"));
					}
				}catch(Exception ee){
					ee.printStackTrace();
				}
				line++;
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		System.out.println("转换成多行code数据：");
		System.out.println(code);
		return code;
	}

	/**
	 * 将t_book格式的一条数据对象转为一条多行的code数据
	 * @param obj
	 * @return
	 */
	public static String obj2Rows(Object obj){
		code = "";
		// 显示行号
		try{
			String tempString = obj2Row(obj);
			code += "LDR "+tempString.substring(0,24)+"\n";
			showMarc(tempString.getBytes("GBK"));
		}catch(Exception e){
			e.printStackTrace();
		}
		return code;
	}

	private static void showMarc(byte[] bytes) {
		try {
			// 读取此条数据的总长度
			byte []marcB = new byte[5];
			for (int i = 0; i < 5; i++) {
				marcB[i] = bytes[i];
			}
			String marcS = new String(marcB);
			int marcLen = Integer.parseInt(marcS);
			/*System.out.println("总长度："+marcLen);*/
			//读取数据基地址
			byte []marcB2 = new byte[5];
			for (int i = 0; i < 5; i++) {
				marcB2[i] = bytes[i + 12];
			}
			String marcS2 = new String(marcB2);
			int dataStart = Integer.parseInt(marcS2);
			/*System.out.println("数据区开始地址："+dataStart);*/
			// 读取次目录区数据
			int cmLength = dataStart - 24 - 1;
			byte []marcB3 = new byte[cmLength];
			for (int i = 0; i < cmLength; i++) {
				marcB3[i] = bytes[i + 24];
			}
			// 读取记录控制信息
			String marcS3 = new String(marcB3);
			/*System.out.println("记录控制区："+marcS3);*/
			int n = cmLength / 12;
			String []controls = new String[n];
			for (int i = 0; i < n; i++) {
				controls[i] = marcS3.substring(i * 12, (i + 1) * 12);
			}
			// 读取数据区信息
			int dataLength = marcLen - dataStart - 1;
			byte []data = new byte[dataLength];
			for (int i = 0; i < dataLength; i++) {
				data[i] = bytes[i + dataStart];
			}
			/*System.out.println("数据区："+new String(data,"GBK"));*/
			String [][]OKData = new String[n][2];
			for (int i = 0; i < n; i++) {
				OKData[i][0] = controls[i].substring(0, 3);
				int length = Integer.parseInt(controls[i].substring(3, 7));
				int start = Integer.parseInt(controls[i].substring(7));
				//不取每个字段数据最后的结束符
				byte []temp = new byte[length-1];
				for (int j = start; j < start + length-1; j++) {
					if(data[j]==31){
						//分隔符
						temp[j - start] = 36;
						//TODO 处理char30
					}else{
						temp[j - start] = data[j];
					}
				}
				OKData[i][1] = new String(temp,"GBK");
				code += OKData[i][0];
				code += OKData[i][1]+"\n";
			}
			code += BREAK_LINE;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 将t_book表格式的多条数据转化成ISO字符
	 * @param list t_book表对应的对象集合
	 * @return String
	 */
	public static String objs2ISOStr(List<Object> list){
		String ISO = "";
		if(list!=null&&list.size()>0){
			for (Object obj:list) {
				ISO += obj2Row(obj)+"\n";
			}
		}
		System.out.println(ISO);
		return ISO;
	}

	/**
	 * 将一条t_book表格式的数据对象转换成一条书目marc数据
	 * @param obj
	 * @return String
	 */
	public static String obj2Row(Object obj){
		//分隔符 RS为字段分隔符，US为子字段分割符，GS为结束符
		String RS = "\u001E",GS = "\u001D",US = "\u001F";
		//头标，目次，内容
		String head = "",table = "",content = "";
		int dataLength = 0;
		try {
			//注意：JSONObject对象属性为空时可能会为"null"字符串
			JSONObject json = JSONUtil.objectToJson(obj);
			//010ISBN编号
			String isbn = "";
			if(json.has("isbn")){
				isbn = json.getString("isbn");
			}
			//010单价
			String price = "";
			if(json.has("price")){
				price = NumberUtil.decimalSup0(Double.valueOf(json.getString("price")),2);
				price = "CNY"+price;
			}
			//101语种
			String language = "";
			if(json.has("language")){
				language = json.getString("language");
				if("中文".equals(language)||"汉语".equals(language)){
					language = "chi";
				}else if("英文".equals(language)||"英语".equals(language)){
					language = "eng";
				}
			}

			//200主题名
			String title = "";
			//200题名拼音
			String titlePinyin = "";
			if(json.has("title")){
				title = json.getString("title");
				//200题名拼音
				titlePinyin = ToPinyinUtil.ToPinyin(title);
			}

			//200次题名
			String secondTitle = "";
			if(json.has("secondTitle")){
				secondTitle = json.getString("secondTitle");
			}
			//200作者、第一责任人
			String author = "";
			if(json.has("author")){
				author = json.getString("author");
			}
			//205版次
			String revision = "";
			if(json.has("revision")){
				revision = json.getString("revision");
			}
			//210出版地区
			String pubArea = "";
			if(json.has("pubArea")){
				pubArea = json.getString("pubArea");
			}
			//210出版社
			String publisher = json.getString("publisher");
			if(json.has("publisher")){
				publisher = json.getString("publisher");
			}
			//210出版日期
			String pubdate = "";
			if(json.has("pubdate")){
				pubdate = json.getString("pubdate");
				pubdate = pubdate=="null"?"":pubdate.substring(0,4);
			}
			//215页数
			String pages = "";
			if(json.has("pages")){
				pages = json.getString("pages");
			}
			//330摘要
			String summary = "";
			if(json.has("summary")){
				summary = json.getString("summary");
			}
			//606主题词
			String subjectWord = "";
			if(json.has("subjectWord")){
				subjectWord = json.getString("subjectWord");
			}
			//690图书分类
			String bookType = "";
			if(json.has("bookType")){
				bookType = json.getString("bookType");
			}
			//701第一责任人
			String firstDuty = "";
			if(json.has("author")){
				firstDuty = json.getString("author");
			}
			//801字段记录来源(国家)，必须字段
			String sourseCountry = "CN";
			//801字段记录来源(机构)，必须字段
			String sourseOrg = "SZZT";
			//801字段记录来源(时间)，必须字段
			String sourseDate = "";
			if(json.has("createTime")){
				sourseDate = json.getString("createTime");
				if(StringUtils.isNotEmpty(sourseDate)&&!"null".equals(sourseDate)){
					sourseDate = sourseDate.substring(0,10).replace("-","");
				}else{
					sourseDate = DateUtils.getDateTime().substring(0,10).replace("-","");
				}
			}

			String data001 = RS+"01"+sourseDate.substring(0,4)+"002367";
			content += data001;
			table += "001"+NumberUtil.frontCompWith0(data001.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
			dataLength += data001.getBytes("GBK").length;

			String time005 = json.getString("createTime");
			if(StringUtils.isEmpty(time005)||"null".equals(time005)){
				time005 = DateUtils.getDateTime();
			}
			String data005 = RS+time005.replace("-","").replace(" ","").replace(":","")+".0";
			content += data005;
			table += "005"+NumberUtil.frontCompWith0(data005.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
			dataLength += data005.getBytes("GBK").length;

			if(StringUtils.isNotEmpty(isbn)&&!"null".equals(isbn)){
				String p = "";
				if(StringUtils.isNotEmpty(price)&&!"null".equals(price)){
					p = US+"d"+price;
				}
				String data010 = RS+"  "+US+"a"+isbn+p;
				content += data010;
				table += "010"+NumberUtil.frontCompWith0(data010.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data010.getBytes("GBK").length;
			}

			if(StringUtils.isNotEmpty(language)&&!"null".equals(language)){
				String data101 = RS+"0 "+US+"a"+language;
				content += data101;
				table += "101"+NumberUtil.frontCompWith0(data101.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data101.getBytes("GBK").length;
			}

			String t2 = "";
			if(StringUtils.isNotEmpty(secondTitle)&&!"null".equals(secondTitle)){
				t2 = US+"e"+secondTitle;
			}
			String a2 = "";
			if(StringUtils.isNotEmpty(author)&&!"null".equals(author)){
				a2 = US+"f"+author+"编著";
			}
			String data200 = RS+"1 "+US+"a"+title+US+"9"+titlePinyin+t2+a2;
			content += data200;
			table += "200"+NumberUtil.frontCompWith0(data200.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
			dataLength += data200.getBytes("GBK").length;

			if(StringUtils.isNotEmpty(revision)&&!"null".equals(revision)){
				String data205 = RS+"  "+US+"a"+revision;
				content += data205;
				table += "205"+NumberUtil.frontCompWith0(data205.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data205.getBytes("GBK").length;
			}

			if((StringUtils.isNotEmpty(pubArea)&&!"null".equals(pubArea))||(StringUtils.isNotEmpty(publisher)&&!"null".equals(publisher))||(StringUtils.isNotEmpty(pubdate)&&!"null".equals(pubdate))){
				String data210 = RS+"  ";
				if(StringUtils.isNotEmpty(pubArea)&&!"null".equals(pubArea)){
					data210 += US+"a"+pubArea;
				}
				if(StringUtils.isNotEmpty(publisher)&&!"null".equals(publisher)){
					data210 += US+"c"+publisher;
				}
				if(StringUtils.isNotEmpty(pubdate)&&!"null".equals(pubdate)){
					data210 += US+"d"+pubdate;
				}
				content += data210;
				table += "210"+NumberUtil.frontCompWith0(data210.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data210.getBytes("GBK").length;
			}

			if(StringUtils.isNotEmpty(pages)&&!"null".equals(pages)){
				String data215 = RS+"  "+US+"a"+pages+"页";
				content += data215;
				table += "215"+NumberUtil.frontCompWith0(data215.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data215.getBytes("GBK").length;
			}

			if(StringUtils.isNotEmpty(summary)&&!"null".equals(summary)){
				String data330 = RS+"  "+US+"a"+summary;
				content += data330;
				table += "330"+NumberUtil.frontCompWith0(data330.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data330.getBytes("GBK").length;
			}

			if(StringUtils.isNotEmpty(subjectWord)&&!"null".equals(subjectWord)){
				String subjectWordPinyin = ToPinyinUtil.ToPinyin(subjectWord);
				String data606 = RS+"0 "+US+"a"+subjectWord+US+"9"+subjectWordPinyin;
				content += data606;
				table += "606"+NumberUtil.frontCompWith0(data606.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data606.getBytes("GBK").length;
			}

			if(StringUtils.isNotEmpty(bookType)&&!"null".equals(bookType)){
				String data690 = RS+"  "+US+"a"+bookType+US+"v4";
				content += data690;
				table += "690"+NumberUtil.frontCompWith0(data690.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data690.getBytes("GBK").length;
			}

			if(StringUtils.isNotEmpty(firstDuty)&&!"null".equals(firstDuty)){
				String firstDutyPinyin = ToPinyinUtil.ToPinyin(firstDuty);
				String data701 = RS+" 0"+US+"a"+firstDuty+US+"9"+firstDutyPinyin+US+"4著";
				content += data701;
				table += "701"+NumberUtil.frontCompWith0(data701.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
				dataLength += data701.getBytes("GBK").length;
			}

			String data801 = RS+" 0"+US+"a"+sourseCountry+US+"b"+sourseOrg+US+"c"+sourseDate;
			content += data801;
			table += "801"+NumberUtil.frontCompWith0(data801.getBytes("GBK").length,4)+NumberUtil.frontCompWith0(dataLength,5);
			dataLength += data801.getBytes("GBK").length;


			int size = table.getBytes("GBK").length + dataLength + (RS+GS).getBytes("GBK").length;
			String dataStart = NumberUtil.frontCompWith0(24+table.getBytes("GBK").length+1,5);
			head = NumberUtil.frontCompWith0(size+24,5)+"nam0 "+"22"+dataStart+"   450 ";
			content += RS+GS;

		} catch (Exception e){
			e.printStackTrace();
		}
		return head + table + content;
	}

}
