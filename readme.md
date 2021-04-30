# WxxMvc
> 思想基于SpringMVC，运行在Tomcat容器中的MVC容器

本框架的诞生原因是因为之前系统的看了[SpringMVC的代码](https://wxxlamp.cn/2021/02/17/spring-mvc-desc/) 
，所以想写一个玩具出来玩一下。

MVC框架解决了什么功能呢？
对于整个流程来说，当一个HTTP请求进入服务器之后，controller会对后台的数据，即model进行加工处理，
然后由框架将这些model渲染为对应的view，形成response响应给client。
可以发现，从MVC的概念上来说，View层应该由服务端来负责渲染。
但是，目前被大家广泛接受的前后端分离的形式，即后台只把model转化为json传递给前端，由前端渲染成View。这样是不属于MVC模式的。
鉴于此，**本框架与普通MVC框架最大的不同点既是抛弃了基于ModelAndView的视图渲染模块**，而是直接返回数据模型由前端渲染。

