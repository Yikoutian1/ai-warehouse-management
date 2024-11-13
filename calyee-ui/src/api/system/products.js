import request from '@/utils/request'

// 查询产品，存储产品信息列表
export function listProducts(query) {
  return request({
    url: '/system/products/list',
    method: 'get',
    params: query
  })
}

// 查询产品，存储产品信息详细
export function getProducts(productId) {
  return request({
    url: '/system/products/' + productId,
    method: 'get'
  })
}

// 新增产品，存储产品信息
export function addProducts(data) {
  return request({
    url: '/system/products',
    method: 'post',
    data: data
  })
}

// 修改产品，存储产品信息
export function updateProducts(data) {
  return request({
    url: '/system/products',
    method: 'put',
    data: data
  })
}

// 删除产品，存储产品信息
export function delProducts(productId) {
  return request({
    url: '/system/products/' + productId,
    method: 'delete'
  })
}
