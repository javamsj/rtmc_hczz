/*package com.crm.listenerTimer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crm.enums.ActivityCheckEnum;
import com.crm.enums.IntergralTypeEnum;
import com.crm.pojo.GwActivityMemberModel;
import com.crm.pojo.GwActivityModel;
import com.crm.pojo.GwMemberIntegralDetailModel;
import com.crm.pojo.GwMembersModel;
import com.crm.service.GwActivityMemberService;
import com.crm.service.GwActivityService;
import com.crm.service.GwMemberIntegralDetailService;
import com.crm.service.GwMembersService;
import com.crm.util.DateUtil;
import com.crm.util.ToolSpring;

public class ActivityListener extends TimerTask {
	
	//活动业务逻辑
	GwActivityService gwActivityServiceImpl = (GwActivityService) ToolSpring.getBean("gwActivityServiceManage");
	//活动会员业务逻辑
	GwActivityMemberService gwActivityMemberServiceImpl = (GwActivityMemberService) ToolSpring.getBean("gwActivityMemberServiceManage");
	//会员业务逻辑
	GwMembersService gwMembersServiceImpl = (GwMembersService) ToolSpring.getBean("gwMembersServiceManage");
	//会员明细积分业务逻辑
	GwMemberIntegralDetailService gwMemberIntegralDetailServiceImpl=(GwMemberIntegralDetailService)ToolSpring.getBean("gwMemberIntegralDetailServiceManage");
	private Logger logger = LoggerFactory.getLogger(ActivityListener.class);

	@Override
	public void run() {
		try {
			Map<String, Object> params = new HashMap<>();
			params.put("activityStatus", ActivityCheckEnum.ALREADYCHECK.getValue());
			List<GwActivityModel> activityList = gwActivityServiceImpl.selectActivityListByCondition(params);
			for (GwActivityModel gwActivityModel : activityList) {
				String statrTime = gwActivityModel.getStartTime();
				String endTime = gwActivityModel.getEndTime();
				String nowTime = DateUtil.getCurrentDatetime("-");
				Map<String, Object> par = new HashMap<>();
				if (nowTime.compareTo(statrTime) >= 0 && endTime.compareTo(nowTime) > 0) {
					par.put("status", ActivityCheckEnum.FINISHED.getValue());
					par.put("id", gwActivityModel.getId());
				}
				int number=0;
				if (par.get("id")!= null&&!"".equals(par.get("id"))) {
					//更新活动状态进行
					number= gwActivityServiceImpl.updateStatusByCondition(par);
				}
				if (number > 0) {
					logger.info("----活动状态改变为进行中-----");
				}
			}
			Map<String, Object> params2 = new HashMap<>();
			params2.put("activityStatus", ActivityCheckEnum.FINISHED.getValue());
			List<GwActivityModel> activityListIng = gwActivityServiceImpl.selectActivityListByCondition(params2);
			for (GwActivityModel gwActivityModel : activityListIng) {
				String endTime = gwActivityModel.getEndTime();
				String nowTime = DateUtil.getCurrentDatetime("-");
				
				Map<String, Object> par = new HashMap<>();
				if (endTime.compareTo(nowTime) < 0) {
					par.put("status", ActivityCheckEnum.FINISHE.getValue());
					par.put("id", gwActivityModel.getId());
				}
				int number=0;
				if (par.get("id")!= null&&!"".equals(par.get("id"))) {
					//活动一旦结束，扣除会员相对积分
					String activitycode=gwActivityModel.getCode();
					int activityIntergral=gwActivityModel.getIntegration();
					List<GwActivityMemberModel> memberActivityList=gwActivityMemberServiceImpl.getActivityMemberByCode(activitycode);
					Map<String, Object> updateParam = new HashMap<>();
					updateParam.put("activityIntergral", activityIntergral);
					for (GwActivityMemberModel model : memberActivityList) {
						updateParam.put("vipCode", model.getMemberCode());
						gwMembersServiceImpl.updateJoinActivityMemberIntergral(updateParam);
						
						//获取会员信息
						GwMembersModel members= gwMembersServiceImpl.getMembersByCode(model.getMemberCode());
						//积分明细插入会员积分变更记录
						GwMemberIntegralDetailModel integralDetai=new GwMemberIntegralDetailModel();
						integralDetai.setCardCode(members.getCardCode());
						integralDetai.setIntergralTime(DateUtil.getCurrentDatetime("-"));
						integralDetai.setMemberCode(model.getMemberCode());
						integralDetai.setIntergralNum(-activityIntergral);
						integralDetai.setIntergralType(IntergralTypeEnum.EXCHANGE.getValue());
						integralDetai.setNote("参加活动(减少)");
						gwMemberIntegralDetailServiceImpl.insert(integralDetai);
					}
					//更新活动状态结束
					number= gwActivityServiceImpl.updateStatusByCondition(par);
				}
				if (number > 0) {
					logger.info("-----活动状态改变为结束---");
				}
			}
			
		} catch (Exception e) {
			logger.info("-------------定时更新活动状态发生异常！-----------");
		}
	}

//	 public static void main(String[] args) {
//		 System.out.println();
//	
//	 }

}
*/