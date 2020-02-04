import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/commentInfo',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/commentInfo/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/commentInfo',
    method: 'put',
    data
  })
}

export function InsertCommentByStudent(data) {
  return request({
    url: 'api/commentInfo/InsertCommentByStudent',
    method: 'post',
    params: data
  })
}

export default { add, edit, del, InsertCommentByStudent }
