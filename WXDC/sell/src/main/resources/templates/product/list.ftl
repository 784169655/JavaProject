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
                                    <th>商品id</th>
                                    <th>名称</th>
                                    <th>图片</th>
                                    <th>单价</th>
                                    <th>库存</th>
                                    <th>描述</th>
                                    <th>类目</th>
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th colspan="2">操作</th>
                                </tr>
                                </thead>
                                <tbody>

                                <#list productInfoPage.content as productInfo>

                                <tr>
                                    <td>${productInfo.productId}<br></td>
                                    <td>${productInfo.productName}<br></td>
                                    <td><img height="100" width="100" src="${productInfo.productIcon}"><br></td>
                                    <td>${productInfo.productPrice}<br></td>
                                    <td>${productInfo.productStock}<br></td>
                                    <td>${productInfo.productDescription}<br></td>
                                    <td>${productInfo.categoryType}<br></td>
                                    <td>${productInfo.createTime}<br></td>
                                    <td>${productInfo.updateTime}<br></td>
                                    <td><a href="/sell/seller/product/index?productId=${productInfo.productId}">修改</a></td>
                                    <td>
                                        <#if productInfo.getProductStatusEnum().message == "上架">
                                            <a href="/sell/seller/product/off_sale?productId=${productInfo.productId}">下架</a>
                                        <#else>
                                            <a href="/sell/seller/product/on_sale?productId=${productInfo.productId}">上架</a>
                                        </#if>
                                    </td>
                                </tr>

                                </#list>

                                </tbody>
                            </table>
                        </div>

                    <#--分页-->
                        <div class="col-md-12 column">
                            <ul class="pagination pull-right">
                            <#if currentPage lte 1>
                                <li class="disabled" ><a href="#">上一页</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${currentPage - 1}&size=${size}">上一页</a></li>
                            </#if>

                            <#list 1..productInfoPage.getTotalPages() as index>

                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}<br></a></li>
                                <#else>
                                    <li><a href="/sell/seller/order/list?page=${index}&size=${size}">${index}<br></a></li>
                                </#if>

                            </#list>

                            <#if currentPage gte productInfoPage.getTotalPages()>
                                <li class="disabled" ><a href="#">下一页</a></li>
                            <#else>
                                <li><a href="/sell/seller/order/list?page=${currentPage + 1}&size=${size}">下一页</a></li>
                            </#if>
                            </ul>
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