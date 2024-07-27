package design_patterns.behave_patterns.mediator;

public class Client {
    public static void main(String[] args) {

        MediatorStructure mediator = new MediatorStructure();
        Tenant tenant = new Tenant("李四", mediator);
        HouseOwner houseOwner = new HouseOwner("张三", mediator);

        mediator.setHouseOwner(houseOwner);
        mediator.setTenant(tenant);

        tenant.contact("我要租房");
        houseOwner.contact("我有别墅");
    }
}
