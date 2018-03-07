通用cms系统项目
<h1>计划</h1>
一个通用的cms系统，基于此系统可以快速的搭建一个网站！<br>
1、后台系统部分<br>
2、freemar静化网站页面<br>
力争在年前完成全部系统的开发！<br>
<h1>开发日志</h1>
2017/10/21 <br>
技术选型：基于spring+springmvc+mybaits框架<br>
                    利用maven分模块管理<br>                          
2017/11/4<br>
整个工程划分三个部分：<br>
cms-parent：用于继承和聚合<br>
cms-dao：通用的service层和dao层，封装了通用了增删改查方法，极大地减少了后续工作量<br>
cms-web：web层工程<br>
这里原计划分模块管理，在后续工作中再决定是否更改吧<br>
2017/11/5<br>
cms-web，搭建SSM框架<br>
后台权限管理模块<br>
2017/11/18<br>
今天完成cms系统的用户管理模块，观看孔浩的视频有感，计划在项目结束时专门做一个测试模块！<br>
2017/12/20<br>
完成栏目的管理，可通过后台系统配置网站的栏目<br>
2018/2/2<br>
添加权限控制模块；不同角色可访问不同的模块，精确到方法级别；最近有所懈怠，年前务必完成整个后台系统的开发<br>
2018/3/7
完成文章管理模块：相关技术1、上传uploadify 2、富文字编辑器xhedit 3、栏木树ztree 3、自动填充、时间控件-jquery ui 4、图像切割thumbnail
在此推荐图像处理的神器！大名鼎鼎的thumbnail 可参考：<a href="https://www.cnblogs.com/fomeiherz/p/5882643.html" alt="链接已失效">thumbnail使用指南</a><br>
  本系统大量图像处理的部分，都使用了该工具、简单方便直接上手：<br>
  <img src="/cms-web/src/main/webapp/resources/upload/indexPic/20180307102445.png"><br>
  基于jcorp和thumbnail实现了上图首页图片的截取！<br>
系统配置部分：<br>
  完成网站信息的管理，采用配置文件的方式填充网站的基本信息<br>
  首页图片管理：包括首页图片和新闻图片<br>
  系统清理功能：对为引用的图片和附件定时清理



<h1>联系方式</h1>
有idea请联系dong0070916@126.com！
