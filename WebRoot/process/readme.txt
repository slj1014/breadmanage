1.三大框架的整合(SSH)
2.新建实体类和映射文件
   user和role是多对多，role和privilege是多对多，privilege和自身是一对多
3.install网数据库中添加权限和管理员
4.index重新定向到indexAction
     在top界面 向右移动 
     <marquee scrollamount="3" direction="right">
                  <font style="color: red" size="4px">你有面包要过期了！！！</font>
     </marquee>
5.配置监听器，在一启动的时候准备好顶级的数据，准备好URL的权限
6.新建一个struts拦截器:CheckPrivilegeInterceptor
        a.根据url进行截取url最后的action字段
        b.如果用户没有登录，就跳转到到登陆界面，或者如果发送的请求时是Login.Action的，就放行
        c.如果该用户已经登录了，判断是否有权限，有就放行，没有就跳转到没有权限的界面
        d.在struts.xml中配置
        e.在配置拦截器的时候顺便配置了表单重复提交的栈
7.分页完成
8.角色的CURD
9.用户的CURD和修改密码
10.面包的图片文件上传
11.自创面包和总部面包完成
11.入库完成
12.销售完成(?????)
13.入库明细
14.面包监听器过期监听器完成
15.用户登录的时候播放提示音乐
16.库存管理完成
17.过期面包查看
18.修改过期的面包
        
技术点:
 1.设置BaseDao泛型接口,因为没个dao的接口都会写CURD，为了代码复用性强，就是用接口
 2.新建BaseAction泛型接口，把所有Service放在里面
 3.修改<s:action>的源代码,把超链接和if判断连接在一起
 