app.controller('brandController',function ($scope,$controller,brandService) {
    $controller('baseController',{$scope:$scope});

    $scope.findAll = function () {
        brandService.findAll().success(function (response) {
            $scope.list = response;
        })
    }



    $scope.findPage = function (pageNum, pageSize) {
        brandService.findPage(pageNum,pageSize).success(function (response) {
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        })
    }


    $scope.save = function () {
        var objectName = "";
        if ($scope.entity.id != null) {
            objectName = brandService.update($scope.entity);
        } else {
            objectName = brandService.add($scope.entity);
        }

        objectName.success(function (response) {
            if (response.success) {
                $scope.reloadList();
            } else {
                alert(response.message);
            }
        })
    }
    $scope.findOne = function (id) {
        brandService.findOne(id).success(function (response) {
            $scope.entity = response;
        })
    }



    $scope.dele = function () {
        brandService.dele($scope.selectId).success(function (response) {
            if (response.success) {
                $scope.reloadList();
                $scope.selectId = [];
            } else {
                alert(response.message);
            }
        })
    }
    $scope.searchEntity = {};
    $scope.search=function (pageNum,pageSize) {
        brandService.search(pageNum,pageSize,$scope.searchEntity).success(function(response){
            $scope.list = response.rows;
            $scope.paginationConf.totalItems = response.total;
        });
    }
})