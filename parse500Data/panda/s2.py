import pandas as pd
import numpy as np
from pandas.api.types import is_string_dtype
import collections
from changeUtils import getPoint


data = pd.DataFrame(pd.read_csv('2.csv')) #读取数据
columns = data.columns
#print(columns)

ppp = 0.25
data_choose = data
# data_choose = data[data[data.columns[6]]==ppp]
#data_choose = data_choose.reset_index(drop=True)
#data_choose = pd.DataFrame(data_choose,columns= data_choose.columns[1:])
columns = data_choose.columns
zhubifen = data_choose['zhubifen'].values
kebifen = data_choose['kebifen'].values
pk = data_choose['pankou'].values


label = []
get_result = []
spfLabel = []


for i in range(len(zhubifen)):
    if np.isnan(zhubifen[i]) or np.isnan(kebifen[i]) or np.isnan(np.isnan(pk[i])):
        label.append(100)
        get_result.append(100)
        spfLabel.append(100)
        continue
    if (pk[i]>1.5 and zhubifen[i]>kebifen[i]) or (pk[i]<-1.5 and zhubifen[i]<kebifen[i]):
        spfLabel.append(100)
    elif zhubifen[i]>kebifen[i]:
        spfLabel.append(0)
    elif zhubifen[i]==kebifen[i]:
        spfLabel.append(1)
    elif zhubifen[i]<kebifen[i]:
        spfLabel.append(2)

    if (zhubifen[i] - kebifen[i] - pk[i])*pk[i]>0:#判定上盘
        label.append(0)
    elif (zhubifen[i] - kebifen[i] - pk[i])*pk[i]<0:
        label.append(1)  #判定下盘
    else:
        label.append(100)
    if (zhubifen[i] - kebifen[i] - pk[i])*pk[i]==0:
        get_result.append(0)
    else:
        if label[-1] == 0 or label[-1]==1: #如果上盘
            cs = 1 if pk[i] > 0 else -1
            p = 1 if label[-1] == 0 else -1
            cs = p*cs
            if (zhubifen[i]-kebifen[i]-pk[i])*cs == 0.25:
                get_result.append(0.5)
            elif (zhubifen[i]-kebifen[i])*cs>0:
                get_result.append(1)
            elif (zhubifen[i]-kebifen[i])*cs == -0.25:
                get_result.append(-0.5)
            else:
                get_result.append(-1)


label = np.asarray(label) #list转ndarray
get_result = np.asarray(get_result)
tpk = np.asarray(pk)
val_idx = np.where(label!=100)[0]
label = label[val_idx]
get_result = get_result[val_idx]
tpk = tpk[val_idx]
data_choose = data.loc[val_idx]
data_choose = data_choose.reset_index(drop=True)

data_values = pd.DataFrame(data_choose, columns=data_choose.columns[10:]) #截取非字符串的特征
f_columns = data_values.columns   #取出所有数值特征的名字


single_rule_threshold = 0.7 #应买样本中，准确度超过这个阈值的规则才被记录
sample_thredhols = 10 #样本数超过这个阈值的规则才被记录


import time
start = time.time()

train_len = len(label)
#train_len = 7268 #7268以后是9月份的

data_train = data_values.loc[range(train_len)]
label_train = label[range(train_len)]


rule_dict = []

pkList = collections.Counter( data_train['pankou'].values  )
for feature_i in f_columns:
    xs = getPoint(feature_i)
    if xs is None:
        xs = getPoint(feature_i[0:len(feature_i)-1])
        if xs is None:
            continue
    collected_values = []
    # for xs in range(1,5):
    for pk in pkList:
        features = data_train[data_train['pankou']==pk]

        feature = np.round(features.values, xs)



        feature_count = collections.Counter(feature)  # 统计特征feature_i 可能存在的数值数目
        for values in feature_count.items():  # 遍历feature_i 的数值
            if values[1] < sample_thredhols:  # 若feature_i 的数值对应的样本数小于这个阈值则跳过
                continue
            if values[0] in collected_values:
                continue
            else:
                collected_values.append(values[0])
            values_pos = np.where(feature == values[0])[0]  # 取出feature_i ==values的样本
            # 判定上下盘在这组样本中的占比
            values_label = label_train[values_pos]
            values_label_1 = len(np.where(values_label == 1)[0])
            values_label_0 = len(np.where(values_label == 0)[0])
            rate_1 = values_label_1 / values[1]
            rate_0 = values_label_0 / values[1]
            if rate_1 > single_rule_threshold or rate_0 > single_rule_threshold:  # 如果某一类占比大于预设的阈值，则记录规则
                if rate_1 > single_rule_threshold:  # 若判定为下盘，记录对应数目
                    rule_dict.append([xs,feature_i,values[0],rate_1,3])
                else:  # 若判定为上盘，记录对应数目
                    rule_dict.append([xs, feature_i, values[0], rate_0,4])

spfLabel = np.asarray(spfLabel) #list转ndarray
val_idx = np.where(spfLabel!=100)[0]
spfLabel = spfLabel[val_idx]
data_choose = data.loc[val_idx]
data_choose = data_choose.reset_index(drop=True)
data_values = pd.DataFrame(data_choose, columns=data_choose.columns[10:]) #截取非字符串的特征
f_columns = data_values.columns

train_len = len(spfLabel)
#train_len = 7268 #7268以后是9月份的

data_train = data_values.loc[range(train_len)]
label_train = spfLabel[range(train_len)]

for feature_i in f_columns:
    xs = getPoint(feature_i)
    if xs is None:
        xs = getPoint(feature_i[0:len(feature_i) - 1])
        if xs is None:
            continue
    collected_values = []
    # for xs in range(1,5):
    feature = np.round(data_train[feature_i].values, xs)
    feature_count = collections.Counter(feature)  # 统计特征feature_i 可能存在的数值数目
    for values in feature_count.items():  # 遍历feature_i 的数值
        if values[1] < sample_thredhols:  # 若feature_i 的数值对应的样本数小于这个阈值则跳过
            continue
        if values[0] in collected_values:
            continue
        else:
            collected_values.append(values[0])
        values_pos = np.where(feature == values[0])[0]  # 取出feature_i ==values的样本
        # 判定胜平负在这组样本中的占比
        values_label = label_train[values_pos]
        values_label_0 = len(np.where(values_label == 0)[0])
        values_label_1 = len(np.where(values_label == 1)[0])
        values_label_2 = len(np.where(values_label == 2)[0])
        rate_0 = values_label_0 / values[1]
        rate_1 = values_label_1 / values[1]
        rate_2 = values_label_2 / values[1]
        if rate_0>single_rule_threshold:
            rule_dict.append([xs, feature_i, values[0], rate_0, 0])
        elif rate_1>single_rule_threshold:
            rule_dict.append([xs, feature_i, values[0], rate_0, 1])
        elif rate_2>single_rule_threshold:
            rule_dict.append([xs, feature_i, values[0], rate_0, 2])

print(rule_dict)