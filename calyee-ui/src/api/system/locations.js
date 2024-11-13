import request from '@/utils/request'

// 查询库位，记录仓库中每个具体存储位置的详细信息列表
export function listLocations(query) {
  return request({
    url: '/system/locations/list',
    method: 'get',
    params: query
  })
}

// 查询库位，记录仓库中每个具体存储位置的详细信息详细
export function getLocations(locationId) {
  return request({
    url: '/system/locations/' + locationId,
    method: 'get'
  })
}

// 新增库位，记录仓库中每个具体存储位置的详细信息
export function addLocations(data) {
  return request({
    url: '/system/locations',
    method: 'post',
    data: data
  })
}

// 修改库位，记录仓库中每个具体存储位置的详细信息
export function updateLocations(data) {
  return request({
    url: '/system/locations',
    method: 'put',
    data: data
  })
}

// 删除库位，记录仓库中每个具体存储位置的详细信息
export function delLocations(locationId) {
  return request({
    url: '/system/locations/' + locationId,
    method: 'delete'
  })
}
