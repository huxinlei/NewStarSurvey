package edu.bdu.controller;

import java.util.List;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bdu.dao.CheckboxItemDaoImpl;
import edu.bdu.dao.ItemDaoImpl;
import edu.bdu.dao.RadioItemDaoImpl;
import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.dao.TextItemDaoImpl;
import edu.bdu.dao.VisitorDaoImpl;

import edu.bdu.entity.CheckboxItem;
import edu.bdu.entity.Item;
import edu.bdu.entity.RadioItem;
import edu.bdu.entity.Survey;
import edu.bdu.entity.TextItem;
import edu.bdu.entity.Visitor;

import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

//import com.lowagie.text.Font;
import java.awt.Font;
import org.jfree.chart.title.TextTitle;

/**
 * 制作日期：2011年8月24日
 * @author xin lei hu
 *
 */

public class CreateResult extends HttpServlet {

	private SurveyDaoImpl surveyOp;//调查问卷操作类
	
	private ItemDaoImpl itemOp;//调查项操作类
	
	private RadioItemDaoImpl radioOp;//单选项操作类
	
	private CheckboxItemDaoImpl checkOp;//多选项操作类
	
	private TextItemDaoImpl textOp;//文字项操作类
	
	private VisitorDaoImpl visitorOp;//访问者操作类
   
	private int width = 550;//图片的宽度
	
	private int height = 400;//图片的高度
	
	public CreateResult() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request, response);//调用doPost方法，执行处理工作
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//设置编码格式
		
		response.setContentType("text/html");//返回结果类型
		response.setCharacterEncoding("UTF-8");//设置编码格式
		
		PrintWriter out = response.getWriter();

		
		//////////////////////////////处理调查问卷代码/////////////////////////////////////
		
		if(request.getParameter("surveyid") != null)
		{
			int surveyid = Integer.parseInt(request.getParameter("surveyid"));//获得调查问卷编号
			
			Survey survey = surveyOp.getSurvey(surveyid);//根据编号获得调查问卷实例
			
			if(survey != null)
			{
				out.println("<div id = 'surveyTitle'>" + survey.getSurveyTitle() + "</div>");//调查问卷标题
				
				
				List items = itemOp.getItemList(survey.getSurveyID());//根据调查问卷获得调查项
				
				if(items.size() == 0)
				{
					out.println("此调查问卷还没有创建调查项！");//打印没有调查项信息
				}
				
				for(int i = 0; i < items.size(); i++)
				{
					Item item = (Item)items.get(i);//获得调查项实例
					
					out.println("<div style='margin-bottom:20px;color:#0099CC;width:759px;padding-bottom:10px;line-height:29px;font-size:16px;font-weight:bold;' align='left'>");
					
					/////////////////////////打印调查问题内容////////////////////////////
					out.println((i + 1) + ". " + item.getItemCaption());
					
					int itemType = item.getItemType();//获得调查项的类型
					
					///////////////////////调查项处理代码/////////////////////////////////////////////////
					String webName = getServletContext().getRealPath("/plotImgs");//获得路径 
				    String prefix = "";
				    String picpath="";
				    String mappath="";
				    
					switch(item.getItemType())
					{
					case 0:
						
						JFreeChart chart = CreateChart(CreateDataset(item.getItemID(), item.getItemType()));
						
						prefix = "radio"+item.getItemID();//由类型加答案项唯一编号
						
						Font font = new Font("宋体",Font.BOLD,16);//设置字体样式
						
						TextTitle title = new TextTitle(item.getItemCaption(),font);
						
						chart.setTitle(title);
						
						
					    //chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,12));
						
						
						picpath = webName + "/" + prefix + ".jpg";
						
						mappath = webName + "/" + prefix + ".map";
						
						FileOutputStream plot_fos = new FileOutputStream(picpath);
						
						
						
						PrintWriter map_pw = new PrintWriter(mappath);
						
					
						
						ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
						
						ChartUtilities.writeChartAsJPEG(plot_fos, 0.8f, chart, width, height, info);
						
						ChartUtilities.writeImageMap(map_pw, "mymap" + item.getItemID(), info, false);
						
						plot_fos.close();//关闭数据流
						
						map_pw.close();//关闭文字流
						
						out.println("<jsp:include page='plotImgs/" + prefix + ".map' />");//访问map热点
						
						out.println("<div>"+
		               "<img id='pic" + item.getItemID() + "' src='plotImgs/"+ prefix + ".jpg' title='统计分析图'" +
		               " alt='正在加载图片，请稍等...' usemap='#mymap" + item.getItemID() + "' style='border:0'>"
	                   + "</div>");
			
						break;
					case 1:
						
		                JFreeChart chart1 = CreateChart(CreateDataset(item.getItemID(), item.getItemType()));
						
						prefix = "radio"+item.getItemID();//由类型加答案项唯一编号
						
						//JFreeChart chart = CreateChart(CreateDataset(item.getItemID(), item.getItemType()));
						
						prefix = "radio"+item.getItemID();//由类型加答案项唯一编号
						
						Font font1 = new Font("宋体",Font.BOLD,16);//设置字体样式
						
						TextTitle title1 = new TextTitle(item.getItemCaption(),font1);
						
						chart1.setTitle(title1);
						
						picpath = webName + "/" + prefix + ".jpg";
						
						mappath = webName + "/" + prefix + ".map";
						
						FileOutputStream plot_fos1 = new FileOutputStream(picpath);
						
						PrintWriter map_pw1 = new PrintWriter(mappath);
						
						
						
						ChartRenderingInfo info1 = new ChartRenderingInfo(new StandardEntityCollection());
						
						ChartUtilities.writeChartAsJPEG(plot_fos1, 0.8f, chart1, width, height, info1);
						
						ChartUtilities.writeImageMap(map_pw1, "mymap" + item.getItemID(), info1, true);
						
						plot_fos1.close();//关闭数据流
						
						map_pw1.close();//关闭文字流
						
                        out.println("<jsp:include page='plotImgs/" + prefix + ".map' />");//访问map热点
						
						out.println("<div>"+
		               "<img id='pic" + item.getItemID() + "' src='plotImgs/"+ prefix + ".jpg' title='统计分析图'" +
		               " alt='正在加载图片，请稍等...' usemap='#mymap" + item.getItemID() + "' style='border:0'>"
	                   + "</div>");
						
						break;
					case 2:
						out.println("<div id='textItem" + item.getItemID() + "'><a href='javascript:getTextList(" + item.getItemID() + ",1)'>查看访问者回答列表</a></div>");//打印访问者列表
						
						break;
					}
					
					///////////////////////调查项处理代码/////////////////////////////////////////////////
					
					////////////////////////打印调查项答案项统计////////////////////////////////
					
					out.println("</div>");
				}
				
			}
			else
			{
				out.println("没有找到此调查问卷！");//没有此调查问卷
			}
			
			
			
		}

		//////////////////////////////处理调查问卷代码/////////////////////////////////////

		out.flush();
		out.close();
	}
   /**
    * 根据传过来的数据生成分析图类
    * @param dataset
    * @return
    */
	private static JFreeChart CreateChart(CategoryDataset dataset)
	{
		
		
		JFreeChart chart = ChartFactory.createBarChart(
				"",
				"调查项",
				"调查项得票数", 
				dataset, 
				PlotOrientation.VERTICAL,
				true, 
				true,
				false
				);
		
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		BarRenderer renderer = (BarRenderer)plot.getRenderer();
		
		/*Font font = new Font("宋体",Font.BOLD,16);//设置字体样式
		
		TextTitle title = new TextTitle("测试wew",font);
		
		chart.setTitle(title);
		//解决jsp中jfreechart乱码问题
		*/
	    //renderer.setDrawBarOutline(true);
	    
	    //renderer.setBarPainter(new StandardBarPainter());
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		
		//设置X轴坐标上的文字，解决中文乱码
		domainAxis.setTickLabelFont(new Font("scan-serif",Font.PLAIN,15));
		
		//设置X轴的标题文字，解决中文乱码
		domainAxis.setLabelFont(new Font("宋体",Font.PLAIN,15));
		
		NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
		
		//设置Y轴坐标上的文字
		numberAxis.setTickLabelFont(new Font("sans-serif",Font.PLAIN,12));
		
		//设置Y轴的标题文字
		numberAxis.setLabelFont(new Font("宋体",Font.PLAIN,12));
		
		//解决底部汉字乱码问题
		chart.getLegend().setItemFont(new Font("宋体",Font.PLAIN,12));
		
		//chart.getLegend().setItemFont(font)
		
		
		domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		
		return chart;
	}
	/**
	 * 获得图像的数据
	 * @param itemid
	 * @param itemType
	 * @return
	 */
	private CategoryDataset CreateDataset(int itemid, int itemType)
	{
	   DefaultCategoryDataset dataset = new DefaultCategoryDataset();//调查问卷
	  
	   switch(itemType)
	   {
	   case 0:
		   List radios = radioOp.getRadioList(itemid);//获得单选调查项的答案项
		   for(int i = 0; i < radios.size(); i++)
		   {
			   RadioItem radio = (RadioItem)radios.get(i);
			   
			   dataset.setValue(radio.getSelectCount(), radio.getRadioCaption(), "");
		   }
		   break;
	   case 1:
		   
		   List checkboxs = checkOp.getCheckboxList(itemid);//获得多选调查项的答案项
		   
		   for(int i = 0; i < checkboxs.size(); i++)
		   {
			   CheckboxItem checkbox = (CheckboxItem)checkboxs.get(i);
			   
			   dataset.setValue(checkbox.getSelectCount(), checkbox.getCheckboxCaption(), "");
		   }
		   
		   break;
	   }
	   
	   return dataset;//返回调查问卷实例
	}
	
	public void init() throws ServletException {
	
		
		this.surveyOp = new SurveyDaoImpl();//实例化调查问卷
		
		this.itemOp = new ItemDaoImpl();//实例化调查项操作类
		
		this.radioOp = new RadioItemDaoImpl();//实例化单选项操作类
		
		this.checkOp = new CheckboxItemDaoImpl();//实例化多选项操作类
		
		this.textOp = new TextItemDaoImpl();//实例化文字项操作类
		
		this.visitorOp = new VisitorDaoImpl();//实例化访问者操作类
	}

}
