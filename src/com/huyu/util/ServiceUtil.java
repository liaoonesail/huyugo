package com.huyu.util;

import com.huyu.entity.PrizeInkind;
import com.huyu.po.Choujiang;

/**
 * 业务Util  功能枚举
 * @author Administrator
 *
 */

public class ServiceUtil {
	
	public static int ZDGL = 34;
	
	/**
	 * 获取交易记录里的渠道枚举,数字转汉字
	 * @param i
	 * @return
	 */
	public static String getPaymentChannel(int i){
		String str = "";
		switch (i) {
		case 1:
			str = "会员开通";
			break;
		case 2:
			str = "商品购买";
			break;
		case 3:
			str = "下级分利";
			break;
		case 4:
			str = "抽奖花费";
			break;
		case 5:
			str = "抽奖获得";
			break;
		case 6:
			str = "提现";
			break;
		case 7:
			str = "签到";
			break;
		case 8:
			str = "分享商品";
			break;
		case 9:
			str = "完善资料";
			break;
		case 10:
			str = "兑换";
			break;
		case 11:
			str = "会员转账";
			break;
		case 12:
			str = "商品退款";
			break;
		case 13:
			str = "商品退货";
			break;
			case 14:
				str="发展金静态收益";
				break;
			case 15:
				str="发展金代奖";
				break;
			case 16:
				str="发展金服务奖";
				break;
			case 17:
				str="发展金管理奖";
				break;
			case 18:
				str="加盟";
				break;
		default:
			str = "其它";
			break;
		}
		return str;
	}
	public static String getPaymentChannelType(int i){
		String str = "";
		switch (i) {
			case 1:
				str = "余额";
				break;
			case 2:
				str = "互余币";
				break;
			case 3:
				str = "抵现金";
				break;
			case 4:
				str = "积分";
				break;
		}
		return str;
	}
	
	/**
	 * 获取显示资金明显
	 * @param receipt 收入
	 * @param payment 支出
	 * @return
	 */
	public static String getPriceDetails(double receipt, double payment){
		if(receipt == 0){
			return "- " + payment;
		}else{
			return "+ " + receipt;
		}
	}
	
	public static Object getChoujiang(int i,PrizeInkind pk){
		Choujiang cj = new Choujiang();
		int jiangType = 0;
		int jiangNum = 0;
		String jiangDetails = "";
		switch (i) {
		case 1:
			jiangType = 1;
			jiangNum = pk.getPrize1();
			jiangDetails = "获得"+pk.getPrize1()+"元红包";
			break;
		case 2:
			jiangType = 1;
			jiangNum = pk.getPrize2();
			jiangDetails = "获得"+pk.getPrize2()+"元红包";
			break;
		case 3:
			jiangType = 1;
			jiangNum = pk.getPrize3();
			jiangDetails = "获得"+pk.getPrize3()+"元红包";
			break;
		case 4:
			jiangType = 1;
			jiangNum = pk.getPrize4();
			jiangDetails = "获得"+pk.getPrize4()+"元红包";
			break;
		case 5:
			jiangType = 1;
			jiangNum = pk.getPrize5();
			jiangDetails = "获得"+pk.getPrize5()+"元红包";
			break;
		case 6:
			jiangType = 0;
			jiangNum = 0;
			jiangDetails = "谢谢参与";
			break;
		default:
			jiangType = 0;
			jiangNum = 0;
			jiangDetails = "谢谢参与";
			break;
		}
		cj.setJiangDetails(jiangDetails);
		cj.setJiangId(i);
		cj.setJiangType(jiangType);
		cj.setJiangNum(jiangNum);
		return cj;
	}
	
}
