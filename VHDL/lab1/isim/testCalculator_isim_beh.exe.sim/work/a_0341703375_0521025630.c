/**********************************************************************/
/*   ____  ____                                                       */
/*  /   /\/   /                                                       */
/* /___/  \  /                                                        */
/* \   \   \/                                                       */
/*  \   \        Copyright (c) 2003-2009 Xilinx, Inc.                */
/*  /   /          All Right Reserved.                                 */
/* /---/   /\                                                         */
/* \   \  /  \                                                      */
/*  \___\/\___\                                                    */
/***********************************************************************/

/* This file is designed for use with ISim build 0x7708f090 */

#define XSI_HIDE_SYMBOL_SPEC true
#include "xsi.h"
#include <memory.h>
#ifdef __GNUC__
#include <stdlib.h>
#else
#include <malloc.h>
#define alloca _alloca
#endif
static const char *ng0 = "E:/workspace_cse260/lab1/calculator.vhd";
extern char *IEEE_P_3620187407;
extern char *IEEE_P_2592010699;
extern char *WORK_P_3505653787;

unsigned char ieee_p_2592010699_sub_1744673427_503743352(char *, char *, unsigned int , unsigned int );
unsigned char ieee_p_2592010699_sub_2545490612_503743352(char *, unsigned char , unsigned char );
char *ieee_p_3620187407_sub_767668596_3965413181(char *, char *, char *, char *, char *, char *);


static void work_a_0341703375_0521025630_p_0(char *t0)
{
    char t1[16];
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;
    char *t9;
    unsigned int t10;
    unsigned int t11;
    unsigned char t12;
    char *t13;
    char *t14;
    char *t15;
    char *t16;
    char *t17;
    char *t18;

LAB0:    xsi_set_current_line(61, ng0);

LAB3:    t2 = (t0 + 2312U);
    t3 = *((char **)t2);
    t2 = (t0 + 2320U);
    t4 = *((char **)t2);
    t5 = (t0 + 1832U);
    t6 = *((char **)t5);
    t5 = (t0 + 1840U);
    t7 = *((char **)t5);
    t8 = ieee_p_3620187407_sub_767668596_3965413181(IEEE_P_3620187407, t1, t3, t4, t6, t7);
    t9 = (t1 + 12U);
    t10 = *((unsigned int *)t9);
    t11 = (1U * t10);
    t12 = (16U != t11);
    if (t12 == 1)
        goto LAB5;

LAB6:    t13 = (t0 + 4984);
    t14 = (t13 + 56U);
    t15 = *((char **)t14);
    t16 = (t15 + 56U);
    t17 = *((char **)t16);
    memcpy(t17, t8, 16U);
    xsi_driver_first_trans_fast(t13);

LAB2:    t18 = (t0 + 4856);
    *((int *)t18) = 1;

LAB1:    return;
LAB4:    goto LAB2;

LAB5:    xsi_size_not_matching(16U, t11, 0);
    goto LAB6;

}

static void work_a_0341703375_0521025630_p_1(char *t0)
{
    char t29[16];
    char *t1;
    unsigned char t2;
    char *t3;
    char *t4;
    unsigned char t5;
    unsigned char t6;
    char *t7;
    char *t8;
    char *t9;
    int t10;
    int t11;
    unsigned int t12;
    int t13;
    unsigned int t14;
    unsigned int t15;
    char *t16;
    char *t17;
    int t18;
    int t19;
    int t20;
    unsigned int t21;
    unsigned int t22;
    char *t23;
    char *t24;
    char *t25;
    char *t26;
    unsigned char t27;
    unsigned char t28;
    unsigned char t30;
    char *t31;
    char *t32;
    char *t33;
    unsigned int t34;
    unsigned char t35;
    unsigned int t36;
    unsigned int t37;
    unsigned int t38;
    unsigned char t39;
    unsigned char t40;

LAB0:    xsi_set_current_line(64, ng0);
    t1 = (t0 + 992U);
    t2 = ieee_p_2592010699_sub_1744673427_503743352(IEEE_P_2592010699, t1, 0U, 0U);
    if (t2 != 0)
        goto LAB2;

LAB4:
LAB3:    t1 = (t0 + 4872);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(65, ng0);
    t3 = (t0 + 1192U);
    t4 = *((char **)t3);
    t5 = *((unsigned char *)t4);
    t6 = (t5 == (unsigned char)3);
    if (t6 != 0)
        goto LAB5;

LAB7:    t1 = (t0 + 1352U);
    t3 = *((char **)t1);
    t5 = *((unsigned char *)t3);
    t6 = (t5 == (unsigned char)3);
    if (t6 == 1)
        goto LAB13;

LAB14:    t2 = (unsigned char)0;

LAB15:    if (t2 != 0)
        goto LAB11;

LAB12:    t1 = (t0 + 1512U);
    t3 = *((char **)t1);
    t5 = *((unsigned char *)t3);
    t6 = (t5 == (unsigned char)3);
    if (t6 == 1)
        goto LAB18;

LAB19:    t2 = (unsigned char)0;

LAB20:    if (t2 != 0)
        goto LAB16;

LAB17:
LAB6:    goto LAB3;

LAB5:    xsi_set_current_line(66, ng0);
    t3 = xsi_get_transient_memory(16U);
    memset(t3, 0, 16U);
    t7 = t3;
    t8 = ((WORK_P_3505653787) + 1168U);
    t9 = *((char **)t8);
    t10 = *((int *)t9);
    t11 = (t10 - 1);
    if (-1 == -1)
        goto LAB8;

LAB9:    t12 = 0;

LAB10:    t13 = (t12 - 15);
    t14 = (t13 * -1);
    t15 = (1U * t14);
    t8 = (t7 + t15);
    t16 = ((WORK_P_3505653787) + 1168U);
    t17 = *((char **)t16);
    t18 = *((int *)t17);
    t19 = (t18 - 1);
    t20 = (0 - t19);
    t21 = (t20 * -1);
    t21 = (t21 + 1);
    t22 = (1U * t21);
    memset(t8, (unsigned char)2, t22);
    t16 = (t0 + 5048);
    t23 = (t16 + 56U);
    t24 = *((char **)t23);
    t25 = (t24 + 56U);
    t26 = *((char **)t25);
    memcpy(t26, t3, 16U);
    xsi_driver_first_trans_fast(t16);
    xsi_set_current_line(67, ng0);
    t1 = (t0 + 5112);
    t3 = (t1 + 56U);
    t4 = *((char **)t3);
    t7 = (t4 + 56U);
    t8 = *((char **)t7);
    *((unsigned char *)t8) = (unsigned char)2;
    xsi_driver_first_trans_fast(t1);
    goto LAB6;

LAB8:    t12 = t11;
    goto LAB10;

LAB11:    xsi_set_current_line(69, ng0);
    t1 = (t0 + 1832U);
    t7 = *((char **)t1);
    t1 = (t0 + 5048);
    t8 = (t1 + 56U);
    t9 = *((char **)t8);
    t16 = (t9 + 56U);
    t17 = *((char **)t16);
    memcpy(t17, t7, 16U);
    xsi_driver_first_trans_fast(t1);
    goto LAB6;

LAB13:    t1 = (t0 + 2472U);
    t4 = *((char **)t1);
    t27 = *((unsigned char *)t4);
    t28 = (t27 == (unsigned char)2);
    t2 = t28;
    goto LAB15;

LAB16:    xsi_set_current_line(71, ng0);
    t1 = (t0 + 2312U);
    t7 = *((char **)t1);
    t1 = (t0 + 2320U);
    t8 = *((char **)t1);
    t9 = (t0 + 1832U);
    t16 = *((char **)t9);
    t9 = (t0 + 1840U);
    t17 = *((char **)t9);
    t23 = ieee_p_3620187407_sub_767668596_3965413181(IEEE_P_3620187407, t29, t7, t8, t16, t17);
    t24 = (t29 + 12U);
    t12 = *((unsigned int *)t24);
    t14 = (1U * t12);
    t30 = (16U != t14);
    if (t30 == 1)
        goto LAB21;

LAB22:    t25 = (t0 + 5048);
    t26 = (t25 + 56U);
    t31 = *((char **)t26);
    t32 = (t31 + 56U);
    t33 = *((char **)t32);
    memcpy(t33, t23, 16U);
    xsi_driver_first_trans_fast(t25);
    xsi_set_current_line(72, ng0);
    t1 = (t0 + 1672U);
    t3 = *((char **)t1);
    t2 = *((unsigned char *)t3);
    t5 = (t2 == (unsigned char)2);
    if (t5 != 0)
        goto LAB23;

LAB25:    xsi_set_current_line(77, ng0);
    t1 = (t0 + 2312U);
    t3 = *((char **)t1);
    t10 = (15 - 15);
    t12 = (t10 * -1);
    t14 = (1U * t12);
    t15 = (0 + t14);
    t1 = (t3 + t15);
    t6 = *((unsigned char *)t1);
    t27 = (t6 == (unsigned char)3);
    if (t27 == 1)
        goto LAB38;

LAB39:    t5 = (unsigned char)0;

LAB40:    if (t5 == 1)
        goto LAB35;

LAB36:    t2 = (unsigned char)0;

LAB37:    if (t2 != 0)
        goto LAB32;

LAB34:    t1 = (t0 + 2312U);
    t3 = *((char **)t1);
    t10 = (15 - 15);
    t12 = (t10 * -1);
    t14 = (1U * t12);
    t15 = (0 + t14);
    t1 = (t3 + t15);
    t6 = *((unsigned char *)t1);
    t27 = (t6 == (unsigned char)2);
    if (t27 == 1)
        goto LAB46;

LAB47:    t5 = (unsigned char)0;

LAB48:    if (t5 == 1)
        goto LAB43;

LAB44:    t2 = (unsigned char)0;

LAB45:    if (t2 != 0)
        goto LAB41;

LAB42:
LAB33:
LAB24:    goto LAB6;

LAB18:    t1 = (t0 + 2472U);
    t4 = *((char **)t1);
    t27 = *((unsigned char *)t4);
    t28 = (t27 == (unsigned char)2);
    t2 = t28;
    goto LAB20;

LAB21:    xsi_size_not_matching(16U, t14, 0);
    goto LAB22;

LAB23:    xsi_set_current_line(73, ng0);
    t1 = (t0 + 2312U);
    t4 = *((char **)t1);
    t10 = (15 - 15);
    t12 = (t10 * -1);
    t14 = (1U * t12);
    t15 = (0 + t14);
    t1 = (t4 + t15);
    t27 = *((unsigned char *)t1);
    t7 = (t0 + 1832U);
    t8 = *((char **)t7);
    t11 = (15 - 15);
    t21 = (t11 * -1);
    t22 = (1U * t21);
    t34 = (0 + t22);
    t7 = (t8 + t34);
    t28 = *((unsigned char *)t7);
    t30 = ieee_p_2592010699_sub_2545490612_503743352(IEEE_P_2592010699, t27, t28);
    t35 = (t30 == (unsigned char)3);
    if (t35 == 1)
        goto LAB29;

LAB30:    t6 = (unsigned char)0;

LAB31:    if (t6 != 0)
        goto LAB26;

LAB28:
LAB27:    goto LAB24;

LAB26:    xsi_set_current_line(74, ng0);
    t17 = (t0 + 5112);
    t23 = (t17 + 56U);
    t24 = *((char **)t23);
    t25 = (t24 + 56U);
    t26 = *((char **)t25);
    *((unsigned char *)t26) = (unsigned char)3;
    xsi_driver_first_trans_fast(t17);
    goto LAB27;

LAB29:    t9 = (t0 + 2632U);
    t16 = *((char **)t9);
    t13 = (15 - 15);
    t36 = (t13 * -1);
    t37 = (1U * t36);
    t38 = (0 + t37);
    t9 = (t16 + t38);
    t39 = *((unsigned char *)t9);
    t40 = (t39 == (unsigned char)2);
    t6 = t40;
    goto LAB31;

LAB32:    xsi_set_current_line(78, ng0);
    t16 = (t0 + 5112);
    t17 = (t16 + 56U);
    t23 = *((char **)t17);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    *((unsigned char *)t25) = (unsigned char)3;
    xsi_driver_first_trans_fast(t16);
    goto LAB33;

LAB35:    t8 = (t0 + 2632U);
    t9 = *((char **)t8);
    t13 = (15 - 15);
    t36 = (t13 * -1);
    t37 = (1U * t36);
    t38 = (0 + t37);
    t8 = (t9 + t38);
    t35 = *((unsigned char *)t8);
    t39 = (t35 == (unsigned char)2);
    t2 = t39;
    goto LAB37;

LAB38:    t4 = (t0 + 1832U);
    t7 = *((char **)t4);
    t11 = (15 - 15);
    t21 = (t11 * -1);
    t22 = (1U * t21);
    t34 = (0 + t22);
    t4 = (t7 + t34);
    t28 = *((unsigned char *)t4);
    t30 = (t28 == (unsigned char)3);
    t5 = t30;
    goto LAB40;

LAB41:    xsi_set_current_line(80, ng0);
    t16 = (t0 + 5112);
    t17 = (t16 + 56U);
    t23 = *((char **)t17);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    *((unsigned char *)t25) = (unsigned char)3;
    xsi_driver_first_trans_fast(t16);
    goto LAB33;

LAB43:    t8 = (t0 + 2632U);
    t9 = *((char **)t8);
    t13 = (15 - 15);
    t36 = (t13 * -1);
    t37 = (1U * t36);
    t38 = (0 + t37);
    t8 = (t9 + t38);
    t35 = *((unsigned char *)t8);
    t39 = (t35 == (unsigned char)3);
    t2 = t39;
    goto LAB45;

LAB46:    t4 = (t0 + 1832U);
    t7 = *((char **)t4);
    t11 = (15 - 15);
    t21 = (t11 * -1);
    t22 = (1U * t21);
    t34 = (0 + t22);
    t4 = (t7 + t34);
    t28 = *((unsigned char *)t4);
    t30 = (t28 == (unsigned char)2);
    t5 = t30;
    goto LAB48;

}

static void work_a_0341703375_0521025630_p_2(char *t0)
{
    char *t1;
    char *t2;
    unsigned char t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;

LAB0:    xsi_set_current_line(87, ng0);

LAB3:    t1 = (t0 + 2472U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t1 = (t0 + 5176);
    t4 = (t1 + 56U);
    t5 = *((char **)t4);
    t6 = (t5 + 56U);
    t7 = *((char **)t6);
    *((unsigned char *)t7) = t3;
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t8 = (t0 + 4888);
    *((int *)t8) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_0341703375_0521025630_p_3(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;

LAB0:    xsi_set_current_line(88, ng0);

LAB3:    t1 = (t0 + 2312U);
    t2 = *((char **)t1);
    t1 = (t0 + 5240);
    t3 = (t1 + 56U);
    t4 = *((char **)t3);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    memcpy(t6, t2, 16U);
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t7 = (t0 + 4904);
    *((int *)t7) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}


extern void work_a_0341703375_0521025630_init()
{
	static char *pe[] = {(void *)work_a_0341703375_0521025630_p_0,(void *)work_a_0341703375_0521025630_p_1,(void *)work_a_0341703375_0521025630_p_2,(void *)work_a_0341703375_0521025630_p_3};
	xsi_register_didat("work_a_0341703375_0521025630", "isim/testCalculator_isim_beh.exe.sim/work/a_0341703375_0521025630.didat");
	xsi_register_executes(pe);
}
