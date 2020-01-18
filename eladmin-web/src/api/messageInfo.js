import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/messageInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/messageInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/messageInfo',
    method: 'put',
    data
  })
}
export function queryMessageInfo(data) {
  return request({
    url: 'api/messageInfo/queryMessageInfo',
    method: 'get',
    data
  })
}

export default { add, edit, del, queryMessageInfo }
