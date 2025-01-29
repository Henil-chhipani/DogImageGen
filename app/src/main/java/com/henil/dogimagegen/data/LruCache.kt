package com.henil.dogimagegen.data
//
//class LruCache<K, V>(private val maxSize: Int) : LinkedHashMap<K, V>(maxSize, 0.75f, true) {
//
//    private val cache: LinkedHashMap<K, V> = LinkedHashMap(0, 0.75f, true)
//
//    fun put(key: K, value: V) {
//        if (cache.size >= maxSize) {
//            val leastRecentlyUsedKey = cache.keys.iterator().next()
//            cache.remove(leastRecentlyUsedKey)
//        }
//        cache[key] = value
//    }
//    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
//        return size > maxSize
//    }
//    fun get(key: K): V? = cache[key]
//
//    fun getAll(): List<V> = cache.values.toList()
//
//    fun clear() = cache.clear()
//}

class LruCache<K, V>(private val maxSize: Int) : LinkedHashMap<K, V>(maxSize, 0.75f, true) {

    override fun removeEldestEntry(eldest: MutableMap.MutableEntry<K, V>?): Boolean {
        return size > maxSize
    }

    fun getAll(): List<V> = values.toList()
}