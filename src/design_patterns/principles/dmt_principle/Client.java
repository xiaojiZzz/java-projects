package design_patterns.principles.dmt_principle;

//迪米特原则：如果两个软件实体无须直接通信，那么就不应当发生直接的相互调用，可以通过第三方转发该调用。
// 其目的是降低类之间的耦合度，提高模块的相对独立性。
public class Client {
    public static void main(String[] args) {
        //创建经纪人类
        Agent agent = new Agent();
        //创建明星类
        Star star = new Star("林青霞");
        agent.setStar(star);
        //创建粉丝对象
        Fans fans = new Fans("xiaoji");
        agent.setFans(fans);
        //创建媒体公司
        Company company = new Company("黑马");
        agent.setCompany(company);

        agent.meeting(); //和粉丝见面
        agent.business(); //和媒体公司洽谈业务
    }
}
