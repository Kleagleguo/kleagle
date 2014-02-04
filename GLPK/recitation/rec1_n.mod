set FOODS;

var quantity{i in FOODS} >=0;

param protein_content{i in FOODS};
param fat_content{i in FOODS};
param carb_content{i in FOODS};
param cal_content{i in FOODS};
param sodium_content{i in FOODS};
param price{i in FOODS};

param protein_min > 0;
param fat_max > 0;
param carb_max > 0;
param cal_max > 0;
param cost_max > 0;


minimize sodium_intake: sum{i in FOODS} sodium_content[i]*quantity[i];

s.t. protein_constraint: sum{i in FOODS} protein_content[i]*quantity[i] >= protein_min;

s.t. fat_constraint: sum{i in FOODS} fat_content[i]*quantity[i] <= fat_max;

s.t. carb_constraint: sum{i in FOODS} carb_content[i]*quantity[i] <= carb_max;

s.t. cal_constraint: sum{i in FOODS} cal_content[i]*quantity[i] <= cal_max;

s.t. cost_constraint: sum{i in FOODS} price[i]*quantity[i] <= cost_max;
