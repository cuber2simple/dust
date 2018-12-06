package o.c.gj.redis;

public interface RedisWrapService {
    /**
     *  为了雪花ID生成,得到当前的serviceId 的序号
     *  最大值为31
     *  最小值为1
     *  超过 31 则为0
     *  1.  服务启动时, 根据serviceId,IP,mac地址  查看当前是否自己的ID(存在就取key 保存)
     *  2.  使用命令  incr serviceId 得到值
     *  3.  当result < 31   设置 serviceId,IP,mac  值为  result
     * @param serviceId
     * @return 当前同服务的ID
     */
    int getServerId(String serviceId);

    /**
     *  使用lua
     *  1. 调用setnx 返回成功 并设置过期值
     *  2. timeout 是秒
     * @param key
     * @param content
     * @param timeout
     * @return lock是否成功
     */
    boolean lock(String key, String content, long timeout);

    /**
     * 删除KEY  使用redis template
     * @param key
     * @return 删除是否成功
     */
    boolean unlock(String key);

    /**
     * 使用redis template
     * @param serviceId
     * @param key
     * @return seq当前值
     */
    long curval(String serviceId, String key);

    /**
     *
     * @param serviceId
     * @param key
     * @param maxValue
     * @return 下个seq值
     */
    long nextval(String serviceId, String key, long maxValue);

    /**
     *
     * 返回redis微秒数
     * @return
     */
    long currentTimeMillis();
}
