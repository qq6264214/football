import copy
import datetime
from itertools import product,combinations

import time

def combination_k(s, k):
    '''
    字符串 s 中选取 k(0 <= k <= len(s)) 个元素，进行组合，以列表的形式返回所有可能的组合
    s --> 输入的字符串
    k --> 选取的元素的个数  
    测试结果如下：
    combination_k('abc', 2) >>> ['ab', 'ac', 'bc']
    
    combination_k('c', 2)   >>> []
        combination_k('c', 2) 的递归内部解释如下：
            --> combination_k('c', 2)
                --> for i in combination_k('', 1):
                        c + i
                    # 由于 combination_k('', 1) 的返回结果是一个空列表，这 for 循环遍历不会被执行，所以返回初始设定的值 []
    '''
    if k == 0: return ['']
    # recursive chain
    subletters = []
        # 此处涉及到一个 python 遍历循环的特点：当遍历的对象为空（列表，字符串...）时，循环不会被执行，range(0) 也是一样
    for i in range(len(s)):
        for letter in combination_k(s[i+1:], k-1):
            subletters += [s[i] + letter]
    return subletters



def combinations_t(iterable, r):
    # combinations('ABCD', 2) --> AB AC AD BC BD CD
    # combinations(range(4), 3) --> 012 013 023 123
    pool = tuple(iterable)
    n = len(pool)
    if r > n:
        return
    indices = list(range(r))
    yield tuple(pool[i] for i in indices)
    while True:
        for i in reversed(range(r)):
            if indices[i] != i + n - r:
                break
        else:
            return
        indices[i] += 1
        for j in range(i+1, r):
            indices[j] = indices[j-1] + 1
        yield tuple(pool[i] for i in indices)




def combine(data, l):
    result = []
    tmp = [0] * l
    length = len(data)

    def next_num(li=0, ni=0):
        if ni == l:
            result.append(copy.copy(tmp))
            return
        for lj in range(li, length):
            tmp[ni] = data[lj]
            next_num(lj + 1, ni + 1)
    next_num()
    print(result)
    return result

def dfs(multiArray, kNums, cur, result,ans):

    if len(result) == kNums:
        ans.append(list(result))
    if len(multiArray) == cur:
        return
    if len(result) < kNums:
        for i in multiArray[cur]:
            result.append(i)
            dfs(multiArray, kNums, cur + 1, result,ans)
            result.pop()
        dfs(multiArray, kNums, cur + 1, result,ans)


def combinations_t(multiArray, kNums):
    ans=[]
    if len(multiArray) >= kNums >= 0:
        dfs(multiArray, kNums, 0, [],ans)

    return ans


def flatten(a):
    b = []
    for i in a:
        if isinstance(i, tuple) or isinstance(i, list):
            b += i
        else:
            b.append(i)

    return b



if __name__ == '__main__':
    # a=['123',('854','asdv','csd')]
    # c = ('854','asdv','csd')
    # b = flatten(a)
    # print(b)

    # a = combinations_t(['a','b','a','c'],2)
    # print(a)

    # a = [['a', 'b', 'c'], ['d', 'e', 'f'], ['g', 'h', 'i'], [1, 2, 3]]
    # k = 3
    #
    # cs = compute(a, k)
    # print(cs)
    # d1 = datetime.datetime.strptime('2019-09-10','%Y-%m-%d')
    # print(d1.strftime('%Y-%m-%d'))
    #
    # # d3 = datetime.date(year=d1.year,month=d1.month,day=d1.day+35)
    # d3 = d1+datetime.timedelta(days=30)
    # print(d3)
    # d2 = datetime.datetime.strptime('2019-09-02','%Y-%m-%d').date()
    # print((d2-d1).days)

    start = time.clock()
    arr=[1]*19
    brr=[2]*19
    # arr2 = [['a','b','c'],['d','e','f'],['a','s','k']]
    # t = product(arr[0])
    # t2 = product(arr2[0],arr2[1],arr2[2])
    # for i,j in zip(t,t2):
    #
    #     print(list(product(i,arr2)))
    # print(type(t))
    at = combinations(arr,11)
    bt = combinations(brr,11)
    for a,b in zip(at,bt):
        print(a,b)



    end = time.clock()
    print("Running time: %s seconds" % (end - start))

