<html>

    <#include "../common/header.ftl">

    <body>
        <div id="wrapper" class="toggled">

            <#--边栏sidebar-->
            <#include "../common/nav.ftl">

            <#--主要内容区域content-->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <table class="table table-hover table-condensed table-bordered">
                                <thead>
                                <tr>
                                    <th>类目id</th>
                                    <th>名称</th>
                                    <th>type</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th >操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <#list categoryList as category>

                                <tr>
                                    <td>${category.categoryId}<br></td>
                                    <td>${category.categoryName}<br></td>
                                    <td>${category.categoryType}<br></td>
                                    <td>${category.createTime}<br></td>
                                    <td>${category.updateTime}<br></td>
                                    <td><a href="/sell/seller/category/index?categoryId=${category.categoryId}">修改</a></td>
                                </tr>

                                </#list>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>


<#--<#list orderDTOPage.content as orderDTO>-->
    <#--${orderDTO.orderId}<br>-->

<#--</#list>-->