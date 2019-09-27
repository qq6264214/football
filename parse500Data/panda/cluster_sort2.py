import pandas as pd
import numpy as np
from pandas.api.types import is_string_dtype
import collections

data = pd.DataFrame(pd.read_csv('1.csv')) #读取数据
columns = data.columns
# print(columns)


data_choose = data
data_choose = data_choose.reset_index(drop=True)
zhubifen = data_choose['zhubifen'].values #主队比分
kebifen = data_choose['kebifen'].values    #客队比分

pk = data_choose['pankou'].values
label = []
for i in range(len(zhubifen)):
    if (zhubifen[i] - kebifen[i] - pk[i])*pk[i]>0:#判定上盘
        label.append(0)
    else:
        label.append(1)  #判定下盘
label = np.asarray(label) #list转ndarray

data_values = pd.DataFrame(data_choose, columns=data_choose.columns[10:]) #截取非字符串的特征
f_columns = data_values.columns   #取出所有数值特征的名字
# title = pd.DataFrame(pd.read_csv('fw.csv'))
# f_columns = []
# for i in data_choose.columns[10:]:
#     if i not in title['title'].values:
#         f_columns.append(i)
# data_values = pd.DataFrame(data_choose, columns=f_columns)


single_rule_threshold = 0.85 #应买样本中，准确度超过这个阈值的规则才被记录
sample_thredhols = 20 #样本数超过这个阈值的规则才被记录
result = pd.DataFrame(columns=['feature', 'value', 'num', '1', '1-acc', '0','0-acc'  ]) # 记录的log
belong = pd.DataFrame(columns=['label'])   #Log的记录，未来用于记录每个样本分别满足哪些规则
belong['label'] = label
all_pos = [] #满足规则的样本id会被放进来

expect = 0
all_pro = 0
class_1 = 0     #应买下盘
class_0 = 0     #应买上盘
class_1_1 = 0   #应买下盘中为下盘的数目
class_0_0 = 0   #应买上盘中为上盘的数目

for feature_i in f_columns:

    for xs in range(1):
        xs = 4 #保留小数点 4
        feature = np.round(data_values[feature_i].values,xs)
        feature_count = collections.Counter(feature) #统计特征feature_i 可能存在的数值数目
        for values in feature_count.items(): #遍历feature_i 的数值

            if values[1]<sample_thredhols: #若feature_i 的数值对应的样本数小于这个阈值则跳过
                continue
            values_pos = np.where(feature==values[0])[0] #取出feature_i ==values的样本

            #判定上下盘在这组样本中的占比
            values_label = label[values_pos]
            values_label_1 = len(np.where(values_label == 1)[0])
            values_label_0 = len(np.where(values_label == 0)[0])
            rate_1 = values_label_1/values[1]
            rate_0 = values_label_0 / values[1]


            if rate_1>single_rule_threshold or rate_0>single_rule_threshold:#如果某一类占比大于预设的阈值，则记录规则

                result.loc[len(result)] = [feature_i, values[0], values[1],values_label_1, round(values_label_1/values[1],2), values_label_0, round(values_label_0/values[1],2)]#log
                all_pos = list(set(all_pos).union(set(values_pos))) #取并集，更新满足的规则
                # print(len(all_pos))
                expect += values[1]/len(label)*max([rate_1,1-rate_1])#近似期望，非期望，可忽略
                all_pro += values[1]/len(label)
                tmp = np.zeros(len(label))
                tmp[values_pos] = 1
                belong[feature_i+str(values[0])] = tmp

                if rate_1>single_rule_threshold: #若判定为下盘，记录对应数目
                    class_1 += values[1]
                    class_1_1 += values_label_1
                else:                            #若判定为上盘，记录对应数目
                    class_0 += values[1]
                    class_0_0 += values_label_0



            # if values[1]>sample_num_threshold:
        # print(values[1] / len(data_values))

        # if values[1] > sample_num_threshold*len(target_pos):
        #
        #
        #
# result.to_csv('pankou=2.csv')
# belong.to_csv('pankou=2_data.csv')


try:
    print('近似期望：{expect}'.format(expect=expect / all_pro))
    print('规则判定为下盘的数目（有重复）：{class_1}，准确度：{acc}'.format(class_1=class_1, acc=round(class_1_1 / class_1,2)))
    print('规则判定为上盘的数目（有重复）：{class_0}，准确度：{acc}'.format(class_0=class_0, acc=round(class_0_0 / class_0,2)))
except:
    1
#import scipy.io as scio
# data_values = belong[belong.columns[1:]].values
# scio.savemat('data_d_'+str(ppp)+'.mat',{'data':data_values[all_pos,:], 'label':label[all_pos]})
# scio.savemat('data_d_all.mat',{'data':data_values[all_pos,:], 'label':label[all_pos]})
1