local serviceCur = KEYS[1]
local serviceNow = KEYS[2]
local max = tonumber(ARGV[1])
local result = 0
local serviceValue = redis.call('get', serviceCur)
if serviceValue ~= nil then
    local curValue = redis.call('incr', serviceNow);
    if curValue <= max then
        result = curValue;
        redis.call('set', serviceCur, curValue);
    end
else
    result = serviceValue
end
return result
