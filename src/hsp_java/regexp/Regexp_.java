package hsp_java.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings({"all"})
//正则表达式的便利
public class Regexp_ {
    public static void main(String[] args) {

        //假定编写了爬虫，从百度页面得到了如下的文本
//        String content = "Java programming language具有大部分编程语言所共有的一些特征，被特意设计用于互联网的分布式环境。" +
//                "Java具有类似于C++语言的\"形式和感觉\"，但它要比C++语言更易于使用，而且在编程时彻底采用了一种\"以对象为导向\"的方式。" +
//                "使用Java编写的应用程序，既可以在一台单独的电脑上运行，也可以被分布在一个网络的服务器端和客户端运行。" +
//                "另外，Java还可以被用来编写容量很小的应用程序模块或者applet，做为网页的一部分使用。" +
//                "applet可使网页使用者和网页之间进行交互式操作。" +
//                "Java是Sun微系统公司在1995年推出的，推出之后马上给互联网的交互式应用带来了新面貌。" +
//                "最常用的两种互联网浏览器软件中都包括一个Java虚拟机。几乎所有的操作系统中都增添了Java编译程序。";

//        String content = "公有地址\n" +
//                "公有地址（Public address）由Inter NIC（Internet Network Information Center因特网信息中心）负责。" +
//                "这些IP地址分配给注册并向Inter NIC提出申请的组织机构。通过它直接访问因特网。\n" +
//                "私有地址\n" +
//                "私有地址（Private address）属于非注册地址，专门为组织机构内部使用。\n" +
//                "以下列出留用的内部私有地址\n" +
//                "A类 10.0.0.0--10.255.255.255\n" +
//                "B类 172.16.0.0--172.31.255.255\n" +
//                "C类 192.168.0.0--192.168.255.255";

        String content = "\">\uE619</i><span class=\"refresh-text_1-d1i\">换一换</span></a></div></div><div class=" +
                "\"opr-toplist1-table_3K7iH\"><div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zM" +
                "d4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT  c-color-red topli" +
                "st1-hot-normal_12THH\" style=\"opacity:1;\"><i class=\"c-icon icon-top_4eWFz\">\uE662</i></spa" +
                "n><a target=\"_blank\" title=\"多地积极恢复和扩大消费\" href=\"/s?wd=%E5%A4%9A%E5%9C%B0%E7%A7%AF%E" +
                "6%9E%81%E6%81%A2%E5%A4%8D%E5%92%8C%E6%89%A9%E5%A4%A7%E6%B6%88%E8%B4%B9&amp;usm=1&amp;ie=utf-8&" +
                "amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=10c1a2VhVPTvHmEWqrNpdea0GkemP7OYRxOIEURFJ" +
                "aesYL76agC8DLhI2Nw&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_1&a" +
                "mp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-colo" +
                "r-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 1, page: 1&#39;" +
                "}\">多地积极恢复和扩大消费</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-t" +
                "d_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   c-index-s" +
                "ingle-hot1\" style=\"opacity:1;\">1</span><a target=\"_blank\" title=\"中国阳后出现肺炎人群约为8%\"" +
                " href=\"/s?wd=%E4%B8%AD%E5%9B%BD%E9%98%B3%E5%90%8E%E5%87%BA%E7%8E%B0%E8%82%BA%E7%82%8E%E4%BA%B" +
                "A%E7%BE%A4%E7%BA%A6%E4%B8%BA8%25&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ip" +
                "dizhi&amp;rsv_t=10c1a2VhVPTvHmEWqrNpdea0GkemP7OYRxOIEURFJaesYL76agC8DLhI2Nw&amp;rqid=a89ff2c10" +
                "07199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_2&amp;rsv_dl=0_right_fyb_pchot_20811&amp" +
                ";sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" dat" +
                "a-click=\"{&#39;clk_info&#39;: &#39;index: 2, page: 1&#39;}\">中国阳后出现肺炎人群约为8%</a><span " +
                "class=\"c-text c-text-hot opr-toplist1-label_3Mevn\">热</span></div></div><div class=\"toplist1" +
                "-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-sing" +
                "le toplist1-hot_2RbQT   c-index-single-hot2\" style=\"opacity:1;\">2</span><a target=\"_blank\"" +
                " title=\"一家四口发烧服用兽药退烧 儿女中毒\" href=\"/s?wd=%E4%B8%80%E5%AE%B6%E5%9B%9B%E5%8F%A3%E5%8" +
                "F%91%E7%83%A7%E6%9C%8D%E7%94%A8%E5%85%BD%E8%8D%AF%E9%80%80%E7%83%A7%20%E5%84%BF%E5%A5%B3%E4%B8" +
                "%AD%E6%AF%92&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=10c1" +
                "a2VhVPTvHmEWqrNpdea0GkemP7OYRxOIEURFJaesYL76agC8DLhI2Nw&amp;rqid=a89ff2c1007199af&amp;rsf=1a9" +
                "8a87e6ca4e38f76305a663a4a5e4e_1_15_3&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pc" +
                "hot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk" +
                "_info&#39;: &#39;index: 3, page: 1&#39;}\">一家四口发烧服用兽药退烧 儿女中毒</a><span class=\"c-text " +
                "c-text-hot opr-toplist1-label_3Mevn\">热</span></div></div><div class=\"toplist1-tr_4kE4D\"><di" +
                "v class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_" +
                "2RbQT   c-index-single-hot3\" style=\"opacity:1;\">3</span><a target=\"_blank\" title=\"乙类乙管" +
                "今开始 这些变化与你相关\" href=\"/s?wd=%E4%B9%99%E7%B1%BB%E4%B9%99%E7%AE%A1%E4%BB%8A%E5%BC%80%E5%A7" +
                "%8B%20%E8%BF%99%E4%BA%9B%E5%8F%98%E5%8C%96%E4%B8%8E%E4%BD%A0%E7%9B%B8%E5%85%B3&amp;usm=1&amp;ie" +
                "=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=5c67ckyCUMr9VgKfbdSMbK2%2Fk9Px0fz%" +
                "2B4YbPHRZvVOXm60NMX%2Bg5xQE0wQU&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4" +
                "a5e4e_1_15_4&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font" +
                "-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 4, " +
                "page: 1&#39;}\">乙类乙管今开始 这些变化与你相关</a></div></div><div class=\"toplist1-tr_4kE4D\"><di" +
                "v class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-ho" +
                "t_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">4</span><a target=\"_blank\" title=" +
                "\"乘客羽绒服炸裂绒毛飘满地铁车厢\" href=\"/s?wd=%E4%B9%98%E5%AE%A2%E7%BE%BD%E7%BB%92%E6%9C%8D%E7%82" +
                "%B8%E8%A3%82%E7%BB%92%E6%AF%9B%E9%A3%98%E6%BB%A1%E5%9C%B0%E9%93%81%E8%BD%A6%E5%8E%A2&amp;usm=1" +
                "&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=5c67ckyCUMr9VgKfbdSMbK2%2Fk9" +
                "Px0fz%2B4YbPHRZvVOXm60NMX%2Bg5xQE0wQU&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a" +
                "663a4a5e4e_1_15_5&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"" +
                "c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;" +
                "index: 5, page: 1&#39;}\">乘客羽绒服炸裂绒毛飘满地铁车厢</a></div></div><div class=\"toplist1-tr_4" +
                "kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single" +
                " toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">5</span><a target=\"_b" +
                "lank\" title=\"《鹿鼎记》主演时隔24年重聚\" href=\"/s?wd=%E3%80%8A%E9%B9%BF%E9%BC%8E%E8%AE%B0%E3%" +
                "80%8B%E4%B8%BB%E6%BC%94%E6%97%B6%E9%9A%9424%E5%B9%B4%E9%87%8D%E8%81%9A&amp;usm=1&amp;ie=utf-" +
                "8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=5c67ckyCUMr9VgKfbdSMbK2%2Fk9Px0fz%2B4" +
                "YbPHRZvVOXm60NMX%2Bg5xQE0wQU&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e" +
                "_1_15_6&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-mediu" +
                " c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 6, page:" +
                " 1&#39;}\">《鹿鼎记》主演时隔24年重聚</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"t" +
                "oplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   to" +
                "plist1-hot-normal_12THH\" style=\"opacity:1;\">6</span><a target=\"_blank\" title=\"江西重大交通事" +
                "故已致19死20伤\" href=\"/s?wd=%E6%B1%9F%E8%A5%BF%E9%87%8D%E5%A4%A7%E4%BA%A4%E9%80%9A%E4%BA%8B%E6" +
                "%95%85%E5%B7%B2%E8%87%B419%E6%AD%BB20%E4%BC%A4&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199" +
                "af&amp;oq=ipdizhi&amp;rsv_t=5c67ckyCUMr9VgKfbdSMbK2%2Fk9Px0fz%2B4YbPHRZvVOXm60NMX%2Bg5xQE0wQU&a" +
                "mp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_7&amp;rsv_dl=0_right_fyb_" +
                "pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtit" +
                "le_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 7, page: 1&#39;}\">江西重大交通事故已致19死" +
                "20伤</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-" +
                "link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style" +
                "=\"opacity:1;\">7</span><a target=\"_blank\" title=\"疑因婆婆怀孕女子被要求打胎\" href=\"/s?wd=%E7%9" +
                "6%91%E5%9B%A0%E5%A9%86%E5%A9%86%E6%80%80%E5%AD%95%E5%A5%B3%E5%AD%90%E8%A2%AB%E8%A6%81%E6%B1%82%E" +
                "6%89%93%E8%83%8E&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=70" +
                "0b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a9" +
                "8a87e6ca4e38f76305a663a4a5e4e_1_15_8&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pcho" +
                "t_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_" +
                "info&#39;: &#39;index: 8, page: 1&#39;}\">疑因婆婆怀孕女子被要求打胎</a></div></div><div class=\"to" +
                "plist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-inde" +
                "x-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">8</span><a targe" +
                "t=\"_blank\" title=\"男子新买百万奔驰被朋友借走撞烂\" href=\"/s?wd=%E7%94%B7%E5%AD%90%E6%96%B0%E4%" +
                "B9%B0%E7%99%BE%E4%B8%87%E5%A5%94%E9%A9%B0%E8%A2%AB%E6%9C%8B%E5%8F%8B%E5%80%9F%E8%B5%B0%E6%92%9" +
                "E%E7%83%82&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=700b23I" +
                "fAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e" +
                "6ca4e38f76305a663a4a5e4e_1_15_9&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20" +
                "811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info" +
                "&#39;: &#39;index: 9, page: 1&#39;}\">男子新买百万奔驰被朋友借走撞烂</a></div></div><div class=\"topl" +
                "ist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-s" +
                "ingle toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">9</span><a target=\"" +
                "_blank\" title=\"城管下跪劝离卖糖葫芦大爷\" href=\"/s?wd=%E5%9F%8E%E7%AE%A1%E4%B8%8B%E8%B7%AA%E5%8A%" +
                "9D%E7%A6%BB%E5%8D%96%E7%B3%96%E8%91%AB%E8%8A%A6%E5%A4%A7%E7%88%B7&amp;usm=1&amp;ie=utf-8&amp;rsv_" +
                "pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELD" +
                "O7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_10&amp;rsv_dl=0_r" +
                "ight_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist" +
                "1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 10, page: 1&#39;}\">城管下跪劝离卖" +
                "糖葫芦大爷</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-topl" +
                "ist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\"" +
                " style=\"opacity:1;\">10</span><a target=\"_blank\" title=\"务工妈妈躺火车座下7小时返乡过年\" href=\"" +
                "/s?wd=%E5%8A%A1%E5%B7%A5%E5%A6%88%E5%A6%88%E8%BA%BA%E7%81%AB%E8%BD%A6%E5%BA%A7%E4%B8%8B7%E5%B0%8" +
                "F%E6%97%B6%E8%BF%94%E4%B9%A1%E8%BF%87%E5%B9%B4&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199a" +
                "f&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqid" +
                "=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_11&amp;rsv_dl=0_right_fyb_pchot" +
                "_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3F" +
                "ULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 11, page: 1&#39;}\">务工妈妈躺火车座下7小时返乡过" +
                "年</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-lin" +
                "k_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"op" +
                "acity:1;\">11</span><a target=\"_blank\" title=\"三亚多人溺水致3死1失联\" href=\"/s?wd=%E4%B8%89%E4%B" +
                "A%9A%E5%A4%9A%E4%BA%BA%E6%BA%BA%E6%B0%B4%E8%87%B43%E6%AD%BB1%E5%A4%B1%E8%81%94&amp;usm=1&amp;ie=ut" +
                "f-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3ow" +
                "WMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_12&amp;" +
                "rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t op" +
                "r-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 12, page: 1&#39;}\"" +
                ">三亚多人溺水致3死1失联</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zM" +
                "d4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-norma" +
                "l_12THH\" style=\"opacity:1;\">12</span><a target=\"_blank\" title=\"郭树清：鼓励住房、汽车等大宗消费\" " +
                "href=\"/s?wd=%E9%83%AD%E6%A0%91%E6%B8%85%EF%BC%9A%E9%BC%93%E5%8A%B1%E4%BD%8F%E6%88%BF%E3%80%81%E6%B1%" +
                "BD%E8%BD%A6%E7%AD%89%E5%A4%A7%E5%AE%97%E6%B6%88%E8%B4%B9&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c10" +
                "07199af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rq" +
                "id=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_13&amp;rsv_dl=0_right_fyb_pchot_" +
                "20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy" +
                "\" data-click=\"{&#39;clk_info&#39;: &#39;index: 13, page: 1&#39;}\">郭树清：鼓励住房、汽车等大宗消费<" +
                "/a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_" +
                "2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opa" +
                "city:1;\">13</span><a target=\"_blank\" title=\"俄议员建议从中国回购辽宁舰\" href=\"/s?wd=%E4%BF%84%E" +
                "8%AE%AE%E5%91%98%E5%BB%BA%E8%AE%AE%E4%BB%8E%E4%B8%AD%E5%9B%BD%E5%9B%9E%E8%B4%AD%E8%BE%BD%E5%AE%81%" +
                "E8%88%B0&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wR" +
                "pgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f7" +
                "6305a663a4a5e4e_1_15_14&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=" +
                "\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;inde" +
                "x: 14, page: 1&#39;}\">俄议员建议从中国回购辽宁舰</a></div></div><div class=\"toplist1-tr_4kE4D\"><div " +
                "class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQ" +
                "T   toplist1-hot-normal_12THH\" style=\"opacity:1;\">14</span><a target=\"_blank\" title=\"“大老虎”" +
                "为孙辈在青岛买两套别墅\" href=\"/s?wd=%E2%80%9C%E5%A4%A7%E8%80%81%E8%99%8E%E2%80%9D%E4%B8%BA%E5%AD%99" +
                "%E8%BE%88%E5%9C%A8%E9%9D%92%E5%B2%9B%E4%B9%B0%E4%B8%A4%E5%A5%97%E5%88%AB%E5%A2%85&amp;usm=1&amp;i" +
                "e=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7x" +
                "oi3owWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_1_15_1" +
                "5&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color" +
                "-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 15, page: 1&#39;}\">" +
                "“大老虎”为孙辈在青岛买两套别墅</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_" +
                "3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-nor" +
                "mal_12THH\" style=\"opacity:1;\">15</span><a target=\"_blank\" title=\"傅政华在北京占用700平米四合院\"" +
                " href=\"/s?wd=%E5%82%85%E6%94%BF%E5%8D%8E%E5%9C%A8%E5%8C%97%E4%BA%AC%E5%8D%A0%E7%94%A8700%E5%B9%B3" +
                "%E7%B1%B3%E5%9B%9B%E5%90%88%E9%99%A2&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipd" +
                "izhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199" +
                "af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_16&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_ri" +
                "ght_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{" +
                "&#39;clk_info&#39;: &#39;index: 16, page: 1&#39;}\">傅政华在北京占用700平米四合院</a></div></div></div" +
                "><div style=\"display:none\"><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-" +
                "toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH" +
                "\" style=\"opacity:1;\">16</span><a target=\"_blank\" title=\"江西车祸伤者：死伤者都是亲友\" href=\"/" +
                "s?wd=%E6%B1%9F%E8%A5%BF%E8%BD%A6%E7%A5%B8%E4%BC%A4%E8%80%85%EF%BC%9A%E6%AD%BB%E4%BC%A4%E8%80%85%E" +
                "9%83%BD%E6%98%AF%E4%BA%B2%E5%8F%8B&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdi" +
                "zhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199a" +
                "f&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_17&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_r" +
                "ight_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"" +
                "{&#39;clk_info&#39;: &#39;index: 1, page: 2&#39;}\">江西车祸伤者：死伤者都是亲友</a></div></div><div " +
                "class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=" +
                "\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">17</span><a " +
                "target=\"_blank\" title=\"多名网红直播间约架在网红桥斗殴\" href=\"/s?wd=%E5%A4%9A%E5%90%8D%E7%BD%91%E7%" +
                "BA%A2%E7%9B%B4%E6%92%AD%E9%97%B4%E7%BA%A6%E6%9E%B6%E5%9C%A8%E7%BD%91%E7%BA%A2%E6%A1%A5%E6%96%97%E6" +
                "%AE%B4&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRp" +
                "gzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f7" +
                "6305a663a4a5e4e_16_30_18&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" clas" +
                "s=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;" +
                "index: 2, page: 2&#39;}\">多名网红直播间约架在网红桥斗殴</a></div></div><div class=\"toplist1-tr_4kE4" +
                "D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single topli" +
                "st1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">18</span><a target=\"_blank\" " +
                "title=\"5万年一遇彗星将造访地球\" href=\"/s?wd=5%E4%B8%87%E5%B9%B4%E4%B8%80%E9%81%87%E5%BD%97%E6%9" +
                "8%9F%E5%B0%86%E9%80%A0%E8%AE%BF%E5%9C%B0%E7%90%83&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c10071" +
                "99af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3owWMVIAIBLELDO7TFwXg&amp;rqi" +
                "d=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_19&amp;rsv_dl=0_right_fyb_pchot" +
                "_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FU" +
                "Ly\" data-click=\"{&#39;clk_info&#39;: &#39;index: 3, page: 2&#39;}\">5万年一遇彗星将造访地球</a></di" +
                "v></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"" +
                "><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;" +
                "\">19</span><a target=\"_blank\" title=\"著名配音艺术家苏秀去世\" href=\"/s?wd=%E8%91%97%E5%90%8D%E9%" +
                "85%8D%E9%9F%B3%E8%89%BA%E6%9C%AF%E5%AE%B6%E8%8B%8F%E7%A7%80%E5%8E%BB%E4%B8%96&amp;usm=1&amp;ie=ut" +
                "f-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=700b23IfAr5wRpgzA9M8ivAeVj2epJ7SPw7xoi3o" +
                "wWMVIAIBLELDO7TFwXg&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_20&a" +
                "mp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-" +
                "t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 4, page: 2&#39;}\">" +
                "著名配音艺术家苏秀去世</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4" +
                " opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_1" +
                "2THH\" style=\"opacity:1;\">20</span><a target=\"_blank\" title=\"一省3正厅被处分！一人有酷吏之名\" hre" +
                "f=\"/s?wd=%E4%B8%80%E7%9C%813%E6%AD%A3%E5%8E%85%E8%A2%AB%E5%A4%84%E5%88%86%EF%BC%81%E4%B8%80%E4%B" +
                "A%BA%E6%9C%89%E9%85%B7%E5%90%8F%E4%B9%8B%E5%90%8D&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c100719" +
                "9af&amp;oq=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxUD40Fh8&" +
                "amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_21&amp;rsv_dl=0_right_fy" +
                "b_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subti" +
                "tle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 5, page: 2&#39;}\">一省3正厅被处分！一人有酷" +
                "吏之名</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1" +
                "-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style" +
                "=\"opacity:1;\">21</span><a target=\"_blank\" title=\"辉瑞新冠药医保谈判谈了4个多小时\" href=\"/s?wd=%" +
                "E8%BE%89%E7%91%9E%E6%96%B0%E5%86%A0%E8%8D%AF%E5%8C%BB%E4%BF%9D%E8%B0%88%E5%88%A4%E8%B0%88%E4%BA%8" +
                "64%E4%B8%AA%E5%A4%9A%E5%B0%8F%E6%97%B6&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq" +
                "=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxUD40Fh8&amp;rqid=" +
                "a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_22&amp;rsv_dl=0_right_fyb_pchot_" +
                "20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FUL" +
                "y\" data-click=\"{&#39;clk_info&#39;: &#39;index: 6, page: 2&#39;}\">辉瑞新冠药医保谈判谈了4个多小时</a" +
                "></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2Y" +
                "UtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacit" +
                "y:1;\">22</span><a target=\"_blank\" title=\"男子务工3年回家路被占 叫挖机拆墙\" href=\"/s?wd=%E7%94%B7" +
                "%E5%AD%90%E5%8A%A1%E5%B7%A53%E5%B9%B4%E5%9B%9E%E5%AE%B6%E8%B7%AF%E8%A2%AB%E5%8D%A0%20%E5%8F%AB%E6" +
                "%8C%96%E6%9C%BA%E6%8B%86%E5%A2%99&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdiz" +
                "hi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxUD40Fh8&amp;rqid=a89ff2c" +
                "1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_23&amp;rsv_dl=0_right_fyb_pchot_20811&am" +
                "p;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data" +
                "-click=\"{&#39;clk_info&#39;: &#39;index: 7, page: 2&#39;}\">男子务工3年回家路被占 叫挖机拆墙</a></di" +
                "v></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUt" +
                "D\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opac" +
                "ity:1;\">23</span><a target=\"_blank\" title=\"成都通报男女干部不雅聊天:建议免职\" href=\"/s?wd=%E6%8" +
                "8%90%E9%83%BD%E9%80%9A%E6%8A%A5%E7%94%B7%E5%A5%B3%E5%B9%B2%E9%83%A8%E4%B8%8D%E9%9B%85%E8%81%8A%E" +
                "5%A4%A9%3A%E5%BB%BA%E8%AE%AE%E5%85%8D%E8%81%8C&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199a" +
                "f&amp;oq=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxUD40Fh8&a" +
                "mp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_24&amp;rsv_dl=0_right_fy" +
                "b_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-su" +
                "btitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 8, page: 2&#39;}\">成都通报男女干部不雅聊" +
                "天:建议免职</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-topl" +
                "ist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" s" +
                "tyle=\"opacity:1;\">24</span><a target=\"_blank\" title=\"出入境打开掀第2轮感染？吴尊友解读\" href=\"/s?" +
                "wd=%E5%87%BA%E5%85%A5%E5%A2%83%E6%89%93%E5%BC%80%E6%8E%80%E7%AC%AC2%E8%BD%AE%E6%84%9F%E6%9F%93%E" +
                "F%BC%9F%E5%90%B4%E5%B0%8A%E5%8F%8B%E8%A7%A3%E8%AF%BB&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c10" +
                "07199af&amp;oq=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxUD4" +
                "0Fh8&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_25&amp;rsv_dl=0_ri" +
                "ght_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1" +
                "-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 9, page: 2&#39;}\">出入境打开掀第2" +
                "轮感染？吴尊友解读</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 op" +
                "r-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12T" +
                "HH\" style=\"opacity:1;\">25</span><a target=\"_blank\" title=\"女子去世后现4针疫苗记录 官方通报\" hr" +
                "ef=\"/s?wd=%E5%A5%B3%E5%AD%90%E5%8E%BB%E4%B8%96%E5%90%8E%E7%8E%B04%E9%92%88%E7%96%AB%E8%8B%97%E8" +
                "%AE%B0%E5%BD%95%20%E5%AE%98%E6%96%B9%E9%80%9A%E6%8A%A5&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c" +
                "1007199af&amp;oq=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxU" +
                "D40Fh8&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_26&amp;rsv_dl=0_r" +
                "ight_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist" +
                "1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 10, page: 2&#39;}\">女子去世后现4针" +
                "疫苗记录 官方通报</a></div></div><div class=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr" +
                "-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH" +
                "\" style=\"opacity:1;\">26</span><a target=\"_blank\" title=\"傅政华：我对孙力军唯命是从\" href=\"/s?w" +
                "d=%E5%82%85%E6%94%BF%E5%8D%8E%EF%BC%9A%E6%88%91%E5%AF%B9%E5%AD%99%E5%8A%9B%E5%86%9B%E5%94%AF%E5%9" +
                "1%BD%E6%98%AF%E4%BB%8E&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_" +
                "t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxUD40Fh8&amp;rqid=a89ff2c1007199af&a" +
                "mp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_27&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_righ" +
                "t_fyb_pchot_20811\" class=\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&" +
                "#39;clk_info&#39;: &#39;index: 11, page: 2&#39;}\">傅政华：我对孙力军唯命是从</a></div></div><div cla" +
                "ss=\"toplist1-tr_4kE4D\"><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c" +
                "-index-single toplist1-hot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">27</span><a " +
                "target=\"_blank\" title=\"女子吐槽68元一盘羊肉只有几片\" href=\"/s?wd=%E5%A5%B3%E5%AD%90%E5%90%90%E6%" +
                "A7%BD68%E5%85%83%E4%B8%80%E7%9B%98%E7%BE%8A%E8%82%89%E5%8F%AA%E6%9C%89%E5%87%A0%E7%89%87&amp;usm" +
                "=1&amp;ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKz" +
                "Qfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxUD40Fh8&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305" +
                "a663a4a5e4e_16_30_28&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=" +
                "\"c-font-medium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;i" +
                "ndex: 12, page: 2&#39;}\">女子吐槽68元一盘羊肉只有几片</a></div></div><div class=\"toplist1-tr_4kE4D\"" +
                "><div class=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-h" +
                "ot_2RbQT   toplist1-hot-normal_12THH\" style=\"opacity:1;\">28</span><a target=\"_blank\" title=\"" +
                "公司年终奖1人1辆电动车,员工回应\" href=\"/s?wd=%E5%85%AC%E5%8F%B8%E5%B9%B4%E7%BB%88%E5%A5%961%E4%BA%B" +
                "A1%E8%BE%86%E7%94%B5%E5%8A%A8%E8%BD%A6%EF%BC%9F%E5%91%98%E5%B7%A5%E5%9B%9E%E5%BA%94&amp;usm=1&amp" +
                ";ie=utf-8&amp;rsv_pq=a89ff2c1007199af&amp;oq=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%" +
                "2FwUR%2Bc%2BKSx37CX%2F4CDxUD40Fh8&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5" +
                "e4e_16_30_29&amp;rsv_dl=0_right_fyb_pchot_20811&amp;sa=0_right_fyb_pchot_20811\" class=\"c-font-m" +
                "edium c-color-t opr-toplist1-subtitle_3FULy\" data-click=\"{&#39;clk_info&#39;: &#39;index: 13, p" +
                "age: 2&#39;}\">公司年终奖1人1辆电动车？员工回应</a></div></div><div class=\"toplist1-tr_4kE4D\"><div c" +
                "lass=\"toplist1-td_3zMd4 opr-toplist1-link_2YUtD\"><span class=\"c-index-single toplist1-hot_2RbQ" +
                "\" href=\"/s?wd=%E6%97%A5%E6%9C%AC60%E5%B2%81%E5%AE%85%E7%94%B7%E7%9C%8B%E5%8A%A8%E6%BC%AB%E8%A2%" +
                "AB%E6%89%93%E6%96%AD%E6%80%92%E6%9D%80%E5%8F%8C%E4%BA%B2&amp;usm=1&amp;ie=utf-8&amp;rsv_pq=a89ff2" +
                "c1007199af&amp;oq=ipdizhi&amp;rsv_t=9b3bQ71BjbB3d6JWHqpaao8xmKzQfj5lU%2FwUR%2Bc%2BKSx37CX%2F4CDxU" +
                "D40Fh8&amp;rqid=a89ff2c1007199af&amp;rsf=1a98a87e6ca4e38f76305a663a4a5e4e_16_30_30&amp;rsv_dl=";


        //提取文章中所有的英文单词
        //提取文章中所有的数字
        //提取文章中所有的数字和英文单词
        //提取ip地址
        //提取热搜
        //传统的方法，使用遍历，代码量大，效率不高
        //正则表达式技术

        //1.先创建一个Pattern对象，模式对象，可以理解为就是一个正则表达式对象
//        Pattern pattern = Pattern.compile("[a-zA-Z]+");
//        Pattern pattern = Pattern.compile("[0-9]+");
//        Pattern pattern = Pattern.compile("([0-9]+)|([a-zA-Z]+)");
//        Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
//        Pattern pattern = Pattern.compile("</span><a target=\"_blank\" title=\"(\\S*)\" href=\"/s?");
//        Pattern pattern = Pattern.compile("\\d\\d?");
//        Pattern pattern = Pattern.compile("^[0-9]+\\w*[a-z]+$");
        Pattern pattern = Pattern.compile("^han");
//        String content2 = "123abc12ab";
        String content2 = "hanxxx hanxxxhan";

        //2.创建一个匹配器对象

        //就是matcher匹配器按照pattern(模式/样式)，到 content 文本中去匹配，找到就返回true，否则返回false
        Matcher matcher = pattern.matcher(content2);
        //3.开始循环匹配
        while (matcher.find()) {
            //匹配内容，文本放到matcher.group(0)中
            System.out.println("找到：" + matcher.group(0));
        }
    }
}
