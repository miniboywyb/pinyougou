app.service('specificationService',function ($http) {
    this.search=function (pageNum, pageSize, searchEntity) {
        $http.post("../specification/search?pageNum="+pageNum+"&pageSize="+pageSize,searchEntity)
    }
})