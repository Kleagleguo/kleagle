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
extern char *IEEE_P_2592010699;
extern char *WORK_P_3505653787;
extern char *IEEE_P_3620187407;

unsigned char ieee_p_2592010699_sub_1744673427_503743352(char *, char *, unsigned int , unsigned int );
unsigned char ieee_p_2592010699_sub_2545490612_503743352(char *, unsigned char , unsigned char );
char *ieee_p_3620187407_sub_767668596_3965413181(char *, char *, char *, char *, char *, char *);


static void work_a_0341703375_0521025630_p_0(char *t0)
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
    char *t30;
    unsigned int t31;
    unsigned char t32;
    unsigned char t33;
    unsigned int t34;
    unsigned int t35;
    unsigned int t36;
    unsigned char t37;

LAB0:    xsi_set_current_line(44, ng0);
    t1 = (t0 + 992U);
    t2 = ieee_p_2592010699_sub_1744673427_503743352(IEEE_P_2592010699, t1, 0U, 0U);
    if (t2 != 0)
        goto LAB2;

LAB4:
LAB3:    t1 = (t0 + 4848);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(45, ng0);
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

LAB5:    xsi_set_current_line(46, ng0);
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
    t16 = (t0 + 4960);
    t23 = (t16 + 56U);
    t24 = *((char **)t23);
    t25 = (t24 + 56U);
    t26 = *((char **)t25);
    memcpy(t26, t3, 16U);
    xsi_driver_first_trans_fast(t16);
    xsi_set_current_line(47, ng0);
    t1 = (t0 + 5024);
    t3 = (t1 + 56U);
    t4 = *((char **)t3);
    t7 = (t4 + 56U);
    t8 = *((char **)t7);
    *((unsigned char *)t8) = (unsigned char)2;
    xsi_driver_first_trans_fast(t1);
    goto LAB6;

LAB8:    t12 = t11;
    goto LAB10;

LAB11:    xsi_set_current_line(49, ng0);
    t1 = (t0 + 1832U);
    t7 = *((char **)t1);
    t1 = (t0 + 4960);
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

LAB16:    xsi_set_current_line(51, ng0);
    t1 = (t0 + 2312U);
    t7 = *((char **)t1);
    t1 = (t0 + 2928U);
    t8 = *((char **)t1);
    t1 = (t8 + 0);
    memcpy(t1, t7, 16U);
    xsi_set_current_line(52, ng0);
    t1 = (t0 + 2312U);
    t3 = *((char **)t1);
    t1 = (t0 + 2320U);
    t4 = *((char **)t1);
    t7 = (t0 + 1832U);
    t8 = *((char **)t7);
    t7 = (t0 + 1840U);
    t9 = *((char **)t7);
    t16 = ieee_p_3620187407_sub_767668596_3965413181(IEEE_P_3620187407, t29, t3, t4, t8, t9);
    t17 = (t0 + 3048U);
    t23 = *((char **)t17);
    t17 = (t23 + 0);
    t24 = (t29 + 12U);
    t12 = *((unsigned int *)t24);
    t14 = (1U * t12);
    memcpy(t17, t16, t14);
    xsi_set_current_line(53, ng0);
    t1 = (t0 + 2312U);
    t3 = *((char **)t1);
    t1 = (t0 + 2320U);
    t4 = *((char **)t1);
    t7 = (t0 + 1832U);
    t8 = *((char **)t7);
    t7 = (t0 + 1840U);
    t9 = *((char **)t7);
    t16 = ieee_p_3620187407_sub_767668596_3965413181(IEEE_P_3620187407, t29, t3, t4, t8, t9);
    t17 = (t29 + 12U);
    t12 = *((unsigned int *)t17);
    t14 = (1U * t12);
    t2 = (16U != t14);
    if (t2 == 1)
        goto LAB21;

LAB22:    t23 = (t0 + 4960);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    t26 = (t25 + 56U);
    t30 = *((char **)t26);
    memcpy(t30, t16, 16U);
    xsi_driver_first_trans_fast(t23);
    xsi_set_current_line(54, ng0);
    t1 = (t0 + 2312U);
    t3 = *((char **)t1);
    t1 = (t0 + 5088);
    t4 = (t1 + 56U);
    t7 = *((char **)t4);
    t8 = (t7 + 56U);
    t9 = *((char **)t8);
    memcpy(t9, t3, 16U);
    xsi_driver_first_trans_fast(t1);
    xsi_set_current_line(55, ng0);
    t1 = (t0 + 1672U);
    t3 = *((char **)t1);
    t2 = *((unsigned char *)t3);
    t5 = (t2 == (unsigned char)2);
    if (t5 != 0)
        goto LAB23;

LAB25:    xsi_set_current_line(62, ng0);
    t1 = (t0 + 2928U);
    t3 = *((char **)t1);
    t10 = (15 - 15);
    t12 = (t10 * -1);
    t14 = (1U * t12);
    t15 = (0 + t14);
    t1 = (t3 + t15);
    t6 = *((unsigned char *)t1);
    t27 = (t6 == (unsigned char)3);
    if (t27 == 1)
        goto LAB43;

LAB44:    t5 = (unsigned char)0;

LAB45:    if (t5 == 1)
        goto LAB40;

LAB41:    t2 = (unsigned char)0;

LAB42:    if (t2 != 0)
        goto LAB37;

LAB39:    t1 = (t0 + 2928U);
    t3 = *((char **)t1);
    t10 = (15 - 15);
    t12 = (t10 * -1);
    t14 = (1U * t12);
    t15 = (0 + t14);
    t1 = (t3 + t15);
    t6 = *((unsigned char *)t1);
    t27 = (t6 == (unsigned char)2);
    if (t27 == 1)
        goto LAB51;

LAB52:    t5 = (unsigned char)0;

LAB53:    if (t5 == 1)
        goto LAB48;

LAB49:    t2 = (unsigned char)0;

LAB50:    if (t2 != 0)
        goto LAB46;

LAB47:
LAB38:
LAB24:    goto LAB6;

LAB18:    t1 = (t0 + 2472U);
    t4 = *((char **)t1);
    t27 = *((unsigned char *)t4);
    t28 = (t27 == (unsigned char)2);
    t2 = t28;
    goto LAB20;

LAB21:    xsi_size_not_matching(16U, t14, 0);
    goto LAB22;

LAB23:    xsi_set_current_line(56, ng0);
    t1 = (t0 + 2928U);
    t4 = *((char **)t1);
    t10 = (15 - 15);
    t12 = (t10 * -1);
    t14 = (1U * t12);
    t15 = (0 + t14);
    t1 = (t4 + t15);
    t27 = *((unsigned char *)t1);
    t28 = (t27 == (unsigned char)3);
    if (t28 == 1)
        goto LAB29;

LAB30:    t6 = (unsigned char)0;

LAB31:    if (t6 != 0)
        goto LAB26;

LAB28:    t1 = (t0 + 2928U);
    t3 = *((char **)t1);
    t10 = (15 - 15);
    t12 = (t10 * -1);
    t14 = (1U * t12);
    t15 = (0 + t14);
    t1 = (t3 + t15);
    t5 = *((unsigned char *)t1);
    t4 = (t0 + 1832U);
    t7 = *((char **)t4);
    t11 = (15 - 15);
    t21 = (t11 * -1);
    t22 = (1U * t21);
    t31 = (0 + t22);
    t4 = (t7 + t31);
    t6 = *((unsigned char *)t4);
    t27 = ieee_p_2592010699_sub_2545490612_503743352(IEEE_P_2592010699, t5, t6);
    t28 = (t27 == (unsigned char)3);
    if (t28 == 1)
        goto LAB34;

LAB35:    t2 = (unsigned char)0;

LAB36:    if (t2 != 0)
        goto LAB32;

LAB33:
LAB27:    goto LAB24;

LAB26:    xsi_set_current_line(57, ng0);
    t9 = (t0 + 5024);
    t16 = (t9 + 56U);
    t17 = *((char **)t16);
    t23 = (t17 + 56U);
    t24 = *((char **)t23);
    *((unsigned char *)t24) = (unsigned char)3;
    xsi_driver_first_trans_fast(t9);
    goto LAB27;

LAB29:    t7 = (t0 + 1832U);
    t8 = *((char **)t7);
    t11 = (15 - 15);
    t21 = (t11 * -1);
    t22 = (1U * t21);
    t31 = (0 + t22);
    t7 = (t8 + t31);
    t32 = *((unsigned char *)t7);
    t33 = (t32 == (unsigned char)3);
    t6 = t33;
    goto LAB31;

LAB32:    xsi_set_current_line(59, ng0);
    t16 = (t0 + 5024);
    t17 = (t16 + 56U);
    t23 = *((char **)t17);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    *((unsigned char *)t25) = (unsigned char)3;
    xsi_driver_first_trans_fast(t16);
    goto LAB27;

LAB34:    t8 = (t0 + 3048U);
    t9 = *((char **)t8);
    t13 = (15 - 15);
    t34 = (t13 * -1);
    t35 = (1U * t34);
    t36 = (0 + t35);
    t8 = (t9 + t36);
    t32 = *((unsigned char *)t8);
    t33 = (t32 == (unsigned char)2);
    t2 = t33;
    goto LAB36;

LAB37:    xsi_set_current_line(63, ng0);
    t16 = (t0 + 5024);
    t17 = (t16 + 56U);
    t23 = *((char **)t17);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    *((unsigned char *)t25) = (unsigned char)3;
    xsi_driver_first_trans_fast(t16);
    goto LAB38;

LAB40:    t8 = (t0 + 3048U);
    t9 = *((char **)t8);
    t13 = (15 - 15);
    t34 = (t13 * -1);
    t35 = (1U * t34);
    t36 = (0 + t35);
    t8 = (t9 + t36);
    t33 = *((unsigned char *)t8);
    t37 = (t33 == (unsigned char)2);
    t2 = t37;
    goto LAB42;

LAB43:    t4 = (t0 + 1832U);
    t7 = *((char **)t4);
    t11 = (15 - 15);
    t21 = (t11 * -1);
    t22 = (1U * t21);
    t31 = (0 + t22);
    t4 = (t7 + t31);
    t28 = *((unsigned char *)t4);
    t32 = (t28 == (unsigned char)3);
    t5 = t32;
    goto LAB45;

LAB46:    xsi_set_current_line(65, ng0);
    t16 = (t0 + 5024);
    t17 = (t16 + 56U);
    t23 = *((char **)t17);
    t24 = (t23 + 56U);
    t25 = *((char **)t24);
    *((unsigned char *)t25) = (unsigned char)3;
    xsi_driver_first_trans_fast(t16);
    goto LAB38;

LAB48:    t8 = (t0 + 3048U);
    t9 = *((char **)t8);
    t13 = (15 - 15);
    t34 = (t13 * -1);
    t35 = (1U * t34);
    t36 = (0 + t35);
    t8 = (t9 + t36);
    t33 = *((unsigned char *)t8);
    t37 = (t33 == (unsigned char)3);
    t2 = t37;
    goto LAB50;

LAB51:    t4 = (t0 + 1832U);
    t7 = *((char **)t4);
    t11 = (15 - 15);
    t21 = (t11 * -1);
    t22 = (1U * t21);
    t31 = (0 + t22);
    t4 = (t7 + t31);
    t28 = *((unsigned char *)t4);
    t32 = (t28 == (unsigned char)2);
    t5 = t32;
    goto LAB53;

}

static void work_a_0341703375_0521025630_p_1(char *t0)
{
    char *t1;
    char *t2;
    unsigned char t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;
    char *t8;

LAB0:    xsi_set_current_line(71, ng0);

LAB3:    t1 = (t0 + 2472U);
    t2 = *((char **)t1);
    t3 = *((unsigned char *)t2);
    t1 = (t0 + 5152);
    t4 = (t1 + 56U);
    t5 = *((char **)t4);
    t6 = (t5 + 56U);
    t7 = *((char **)t6);
    *((unsigned char *)t7) = t3;
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t8 = (t0 + 4864);
    *((int *)t8) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}

static void work_a_0341703375_0521025630_p_2(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;

LAB0:    xsi_set_current_line(72, ng0);

LAB3:    t1 = (t0 + 2312U);
    t2 = *((char **)t1);
    t1 = (t0 + 5216);
    t3 = (t1 + 56U);
    t4 = *((char **)t3);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    memcpy(t6, t2, 16U);
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t7 = (t0 + 4880);
    *((int *)t7) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}


extern void work_a_0341703375_0521025630_init()
{
	static char *pe[] = {(void *)work_a_0341703375_0521025630_p_0,(void *)work_a_0341703375_0521025630_p_1,(void *)work_a_0341703375_0521025630_p_2};
	xsi_register_didat("work_a_0341703375_0521025630", "isim/top_isim_beh.exe.sim/work/a_0341703375_0521025630.didat");
	xsi_register_executes(pe);
}
