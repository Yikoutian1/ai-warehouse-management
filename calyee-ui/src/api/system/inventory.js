import request from '@/utils/request'

// 查询库存，记录库存量及位置信息列表
export function listInventory(query) {
  return request({
    url: '/system/inventory/list',
    method: 'get',
    params: query
  })
}

// 查询库存，记录库存量及位置信息详细
export function getInventory(inventoryId) {
  return request({
    url: '/system/inventory/' + inventoryId,
    method: 'get'
  })
}

// 新增库存，记录库存量及位置信息
export function addInventory(data) {
  return request({
    url: '/system/inventory',
    method: 'post',
    data: data
  })
}

// 修改库存，记录库存量及位置信息
export function updateInventory(data) {
  return request({
    url: '/system/inventory',
    method: 'put',
    data: data
  })
}

// 删除库存，记录库存量及位置信息
export function delInventory(inventoryId) {
  return request({
    url: '/system/inventory/' + inventoryId,
    method: 'delete'
  })
}
