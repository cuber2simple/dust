local seqKey = KEYS[1]
local max = tonumber(ARGV[1])
local result = 0
local nextval = redis.call('incr', seqKey)
if nextval > max  then
    redis.call('set', result);
else
    result = nextval
end
return result
