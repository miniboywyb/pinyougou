app.service('specificationService',function ($http) {
    this.search=function (pageNum, pageSize, searchEntity) {
        return $http.post("../specification/search?pageNum=" + pageNum + "&pageSize=" + pageSize, searchEntity)
    }

    this.add = function (entity) {
        return $http.post('../specification/add', entity);
    }

    this.update = function (entity) {
        return $http.post('../specification/update', entity);
    }
    this.findOne = function (id) {
        return $http.post('../specification/findOne?id=' + id);
    }
    this.dele = function (selectId) {
        return $http.get('../specification/dele?ids=' + selectId);
    }
})