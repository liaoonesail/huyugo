package com.huyu.threadDay;

import com.huyu.entity.FazhanOrder;
import com.huyu.entity.Fazhanjin;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.service.FazhanOrderService;
import com.huyu.service.FazhanjinService;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.UserService;
import com.huyu.util.OrderNum;
import org.hibernate.annotations.SourceType;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
public class FazhanSystem{
    private FazhanOrderService fservice=(FazhanOrderService) ApplicationContextUtil.getBean("fservice");
    private UserService service=(UserService) ApplicationContextUtil.getBean("userService");
    private Receipt_PaymentService rpservice= (Receipt_PaymentService) ApplicationContextUtil.getBean("rpservice");
    private FazhanjinService fazhanjinService=(FazhanjinService)ApplicationContextUtil.getBean("fazhanjinService");
    List<Integer> list=new ArrayList<>();
    Fazhanjin faZhanJin=fazhanjinService.getFazhanjin();
    public void FazhanSystem(){
        List<User> list=service.getByFazhanStatus();
        if(list.size()>0){
            for (User user:list) {
                double guanlijiang=0;
                double fuwujiang=0;
                double daijiang=0;
                List<User> lowerLevelListF = service.LowerLevelListF(user.getId());
                if(lowerLevelListF.size()>=10){
                    double daijinagTotal=0;
                    double fuwujiangTotal=0;
                    for (User user1:lowerLevelListF) {
                       daijinagTotal+=daijiang(user1);
                       if(isFuwu(user1)){
                           fuwujiangTotal+=fuwujiang(user1);
                       }
                    }
                    guanlijiang=(daijinagTotal+fuwujiangTotal)*faZhanJin.getGuanli();//管理奖
                }
                daijiang=daijiang(user);
                fuwujiang=fuwujiang(user);//服务奖

                //更改个人静态收益，个人代奖、个人的服务奖、个人的管理奖（管理奖包含一代的代奖和一代的服务奖的5%）
                List<FazhanOrder> orderList=fservice.listByUserId(user.getId());
                double jintai=0;
                double jifen=0;
                for (FazhanOrder order:orderList) {
                    jintai+=order.getPayMoney()*faZhanJin.getShifang()*faZhanJin.getShifangY();//静态奖
                    jifen+=order.getPayMoney()*faZhanJin.getShifang()*faZhanJin.getShifangJ();//静态积分
                    order.setFazhanjin(order.getFazhanjin()-order.getPayMoney()*faZhanJin.getShifang());
                    order.setDays(order.getDays()-1);
                    fservice.updatejintai(order);
                }
                //double todayadd=jintai+daijiang+fuwujiang+guanlijiang;//当天个人收益
                double todayadd=jintai;
                if(jintai!=0){

                    rpservice.add(new Receipt_Payment(jintai,0, OrderNum.getregTime(),user.getId(),1,14,0,0,0));
                }
               /* if(daijiang!=0){

                    rpservice.add(new Receipt_Payment(daijiang,0, OrderNum.getregTime(),user.getId(),1,15,0,0,0));
                }
                if(fuwujiang!=0){

                    rpservice.add(new Receipt_Payment(fuwujiang,0, OrderNum.getregTime(),user.getId(),1,16,0,0,0));
                }
                if(guanlijiang!=0){

                    rpservice.add(new Receipt_Payment(guanlijiang,0, OrderNum.getregTime(),user.getId(),1,17,0,0,0));
                }*/
                List<FazhanOrder> orderList2=fservice.listByUserId(user.getId());
                double fazhanjin=0;
                for (FazhanOrder order2:orderList2) {
                    fazhanjin+=order2.getFazhanjin();
                }
                user.setAccount(user.getAccount()+todayadd);
                user.setFazhanjin(fazhanjin);
                rpservice.add(new Receipt_Payment(jifen,0, OrderNum.getregTime(),user.getId(),4,14,0,0,0));
                user.setIntegral(user.getIntegral()+jifen);
                service.update(user);
            }
        }

    }

    /**
     * 需要判断的人数ID
     * @param user
     * @return
     */

    public void idList(User user){
        List<User> lowerLevelListF = service.LowerLevelListF(user.getId());
        if(lowerLevelListF.size()>0){
            int i=0;
            for (User user1:lowerLevelListF) {
                if(isFuwu(user1)){
                  break;
                }
                i++;
                list.add(user1.getId());
                idList(user1);
            }
        }
    }

    /**
     * 获取user的服务奖
     * @param user
     * @return
     */
    public double fuwujiang(User user){
        double fuwujing=0;

        if(isFuwu(user)){
            idList(user);
            if(list.size()>0){
                for (int id:list) {
                    FazhanOrder order=fservice.getFirstById(id);
                    if(order!=null){
                        fuwujing+=order.getPayMoney()*faZhanJin.getFuwu();
                    }
                }
            }
        }
        return fuwujing;
    }
    /**
     * 判断是否具有服务奖
     * @param user
     * @return
     */
    public boolean isFuwu(User user){
        if(!isJiameng(user))return false;
        if(user.getIsfuwu()==0){
            List<User> lowerLevelListF = service.LowerLevelListF(user.getId());
            if(lowerLevelListF.size()>=10){
                double payTotal=0;
                for (User user1:lowerLevelListF) {
                    payTotal+=fservice.getPayTotalByUserId(user1.getId());
                }

                if(payTotal>=faZhanJin.getFuwujin()){
                return true;
            }
            }
        }else if(user.getIsfuwu()==1){
            return false;
        }else if(user.getIsfuwu()==2){
            return true;
        }
        return false;
    }

    /**
     * 获取user的代奖
     * @param user
     * @return
     */
    public double daijiang(User user){
        double daijinagTotal=0;
        List<User> llowerLevelListF =service.LowerLevelListF(user.getId());
        if(llowerLevelListF.size()>0){
            for (User user2:llowerLevelListF) { //我下级的一代
                List<FazhanOrder> dayList=fservice.getListByToday(user2.getId());//我下级一代当天购买订单集合
                if(dayList.size()>0){
                    for (FazhanOrder order:dayList) {
                        daijinagTotal+=order.getPayMoney()*faZhanJin.getDai1();
                    }
                }
                List<User> lllowerLevelListF = service.LowerLevelListF(user2.getId());
                if(lllowerLevelListF.size()>0){
                    for (User user3:lllowerLevelListF) { //我下级的二代
                        List<FazhanOrder> ldayList=fservice.getListByToday(user3.getId());//我下级二代当天购买订单集合
                        if(ldayList.size()>0){
                            for (FazhanOrder lorder:ldayList) {
                                daijinagTotal+=lorder.getPayMoney()*faZhanJin.getDai2();
                            }
                        }
                        List<User> llllowerLevelListF = service.LowerLevelListF(user3.getId());
                        if(llllowerLevelListF.size()>0){
                            for (User user4:llllowerLevelListF) { //我下级的三代
                                List<FazhanOrder> lldayList=fservice.getListByToday(user4.getId());//我下级三代当天购买订单集合
                                if(lldayList.size()>0){
                                    for (FazhanOrder llorder:lldayList) {
                                        daijinagTotal+=llorder.getPayMoney()*faZhanJin.getDai3();
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return daijinagTotal;
    }

    /**
     * 发展金除了静态奖其他的奖项
     * @param user
     * @param order
     */
    public void systemsecond(User user,FazhanOrder order){
        User fwuser=fwuser(user);
        if(fwuser!=null&&user.getFazhanStatus()!=1){
            double fuwujiang=order.getPayMoney()*faZhanJin.getFuwu();
            fwuser.setAccount(fwuser.getAccount()+fuwujiang);
            rpservice.add(new Receipt_Payment(fuwujiang,0, OrderNum.getregTime(),fwuser.getId(),1,16,0,0,0));
            service.update(fwuser);
            User sfwuser=service.getid(fwuser.getSuperior_id());
            if(sfwuser!=null){
                double guanli=0;
                if(isGuanli(sfwuser)){
                    guanli=fuwujiang*faZhanJin.getGuanli();//代奖中服务奖的5%
                    rpservice.add(new Receipt_Payment(guanli,0, OrderNum.getregTime(),sfwuser.getId(),1,17,0,0,0));
                }
                sfwuser.setAccount(sfwuser.getAccount()+guanli);
                service.update(sfwuser);
            }
        }


        User suser=service.getid(user.getSuperior_id());
        User ssuser=null;
        User sssuser=null;
        User ssssuser=null;//这里因为管理奖多算一级
        if(suser!=null){
            double dai1=0;
            double dai2=0;
            double dai3=0;
            if(isJiameng(suser)){
                dai1=order.getPayMoney()*faZhanJin.getDai1();
                rpservice.add(new Receipt_Payment(dai1,0, OrderNum.getregTime(),suser.getId(),1,15,0,0,0));
                suser.setAccount(suser.getAccount()+dai1);
                service.update(suser);//修改账户
            }
            ssuser=service.getid(suser.getSuperior_id());
            if(ssuser!=null){
                double guanli1=0;
                if(isGuanli(ssuser)&&dai1!=0){
                    guanli1=dai1*faZhanJin.getGuanli();
                    rpservice.add(new Receipt_Payment(guanli1,0, OrderNum.getregTime(),ssuser.getId(),1,17,0,0,0));
                }
                if(isJiameng(ssuser)){
                    dai2=order.getPayMoney()*faZhanJin.getDai2();
                    rpservice.add(new Receipt_Payment(dai2,0, OrderNum.getregTime(),ssuser.getId(),1,15,0,0,0));
                    ssuser.setAccount(ssuser.getAccount()+dai2+guanli1);
                    service.update(ssuser);//修改账户
                }
                sssuser=service.getid(ssuser.getSuperior_id());
                if(sssuser!=null){
                    double guanli2=0;
                    if(isGuanli(sssuser)&&dai2!=0){
                        guanli2=dai2*faZhanJin.getGuanli();
                        rpservice.add(new Receipt_Payment(guanli2,0, OrderNum.getregTime(),sssuser.getId(),1,17,0,0,0));
                    }
                    if(isJiameng(sssuser)){
                        dai3=order.getPayMoney()*faZhanJin.getDai3();
                        rpservice.add(new Receipt_Payment(dai3,0, OrderNum.getregTime(),sssuser.getId(),1,15,0,0,0));
                        sssuser.setAccount(sssuser.getAccount()+dai3+guanli2);
                        service.update(sssuser);//修改账户
                    }
                    ssssuser=service.getid(sssuser.getSuperior_id());
                    if(ssssuser!=null){
                        double guanli3=0;
                        if(isGuanli(ssssuser)&&dai3!=0){
                            guanli3=dai3*faZhanJin.getGuanli();
                            rpservice.add(new Receipt_Payment(guanli3,0, OrderNum.getregTime(),ssssuser.getId(),1,17,0,0,0));
                        }
                        if(isJiameng(ssssuser)){
                            ssssuser.setAccount(ssssuser.getAccount()+guanli3);
                            service.update(ssssuser);//修改账户
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取user的说有上级的第一个有服务奖的用户
     * @param user
     * @return
     */
    private User fwuser(User user){
        User fuser=service.getid(user.getSuperior_id());
        if(fuser!=null){
            if(isFuwu(fuser)){
                return fuser;
            }else{
                return fwuser(fuser);
            }
        }else{
            return null;
        }
    }

    /**
     * 是否具有管理奖
     * @param user
     * @return
     */
    private boolean isGuanli(User user){
        List<User> lowerLevelListF = service.LowerLevelListF(user.getId());
        if(lowerLevelListF.size()>=1)return true;
        else return false;
    }

    /**
     * 是否加盟
     * @param user
     * @return
     */
    private boolean isJiameng(User user){
        if(user.getFazhanStatus()==1)return true;
        else return false;
    }
}
