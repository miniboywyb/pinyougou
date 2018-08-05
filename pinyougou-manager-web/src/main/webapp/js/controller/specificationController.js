app.controller('specificationController',function ($scope,$controller,specificationService) {
    $controller('baseController',{$scope:$scope});

    $scope.searchEntity={}
    $scope.search=function(pageNum,pageSize){
        specificationService.search(pageNum,pageSize,$scope.searchEntity).success(function (response) {
            $scope.list=response.rows;
            $scope.paginationConf.totalItems=response.total;
        })
    }
})