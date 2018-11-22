-- Set a lock
--  如果获取锁成功，则返回 1
local key     = KEYS[1]
local content = KEYS[2]
local ttl     = ARGV[1]
local lockSet = redis.call('setnx', key, content)
local result = false
if lockSet == 1 then
    redis.call('expire', key, ttl)
    result = true
end
return result