import request from '@/utils/request'

// 查询出入库记录，记录每次库存变动列表
export function listTransactions(query) {
  return request({
    url: '/system/transactions/list',
    method: 'get',
    params: query
  })
}

// 查询出入库记录，记录每次库存变动详细
export function getTransactions(transactionId) {
  return request({
    url: '/system/transactions/' + transactionId,
    method: 'get'
  })
}

// 新增出入库记录，记录每次库存变动
export function addTransactions(data) {
  return request({
    url: '/system/transactions',
    method: 'post',
    data: data
  })
}

// 修改出入库记录，记录每次库存变动
export function updateTransactions(data) {
  return request({
    url: '/system/transactions',
    method: 'put',
    data: data
  })
}

// 删除出入库记录，记录每次库存变动
export function delTransactions(transactionId) {
  return request({
    url: '/system/transactions/' + transactionId,
    method: 'delete'
  })
}
