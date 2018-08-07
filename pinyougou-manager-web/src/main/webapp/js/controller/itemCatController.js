//控制层
app.controller('itemCatController', function ($scope, $controller, itemCatService, typeTemplateService) {

    $controller('baseController', {$scope: $scope});//继承

    //读取列表数据绑定到表单中  
    $scope.findAll = function () {
        itemCatService.findAll().success(
            function (response) {
                $scope.list = response;
            }
        );
    }

    //分页
    $scope.findPage = function (page, rows) {
        itemCatService.findPage(page, rows).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }

    //查询实体
    $scope.findOne = function (id) {
        itemCatService.findOne(id).success(
            function (response) {
                $scope.entity = response;
            }
        );
    }

    //保存
    $scope.save = function () {
        var serviceObject;//服务层对象
        if ($scope.entity.id != null) {//如果有ID
            serviceObject = itemCatService.update($scope.entity); //修改
        } else {
            $scope.entity.parentId = $scope.parentId;
            serviceObject = itemCatService.add($scope.entity);//增加
        }
        serviceObject.success(
            function (response) {
                if (response.success) {
                    //重新查询
                    $scope.findByParentId($scope.parentId);//重新加载
                } else {
                    alert(response.message);
                }
            }
        );
    }


    //批量删除

    $scope.dele = function () {
        //获取选中的复选框
        var ifSearch = 1;
        itemCatService.dele(ifSearch, $scope.selectId).success(
            function (response) {
                if (response.message != "") {
                    var flag = confirm(response.message + "之下有子列表，是否确认删除？");
                    if (flag) {
                        ifSearch = 0;
                        itemCatService.dele(ifSearch, $scope.selectId).success(
                            function (response) {
                                if (response.success) {
                                    $scope.findByParentId($scope.parentId);//刷新列表
                                }
                            })
                    }
                } else {
                    $scope.findByParentId($scope.parentId);//刷新列表
                }
                $scope.selectId = [];
            })
    }


    $scope.searchEntity = {};//定义搜索对象

    //搜索
    $scope.search = function (page, rows) {
        itemCatService.search(page, rows, $scope.searchEntity).success(
            function (response) {
                $scope.list = response.rows;
                $scope.paginationConf.totalItems = response.total;//更新总记录数
            }
        );
    }
    $scope.parentId = 0;

    $scope.findByParentId = function (parentId) {
        $scope.parentId = parentId;
        itemCatService.findByParentId(parentId).success(function (response) {
            $scope.list = response;
        })
    }

    $scope.grade = 1;

    $scope.setGrade = function (value) {
        $scope.grade = value;
    };

    $scope.selectList = function (p_entity) {
        if ($scope.grade == 1) {
            $scope.entity_1 = null;
            $scope.entity_2 = null;
        }

        if ($scope.grade == 2) {
            $scope.entity_1 = p_entity;
            $scope.entity_2 = null;
        }
        if ($scope.grade == 3) {
            $scope.entity_2 = p_entity;
        }
        $scope.findByParentId(p_entity.id);
    }

    $scope.findTypeTemplateList = function () {
        typeTemplateService.findTypeTemplateList().success(function (response) {
            $scope.typeTemplateList = {data: response};
        })
    }
});	
