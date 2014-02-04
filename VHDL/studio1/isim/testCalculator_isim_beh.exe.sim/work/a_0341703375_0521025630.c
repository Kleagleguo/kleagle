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
static const char *ng0 = "E:/workspace_cse260/studio1/calculator.vhd";
extern char *IEEE_P_2592010699;
extern char *WORK_P_3505653787;
extern char *IEEE_P_3620187407;

unsigned char ieee_p_2592010699_sub_1744673427_503743352(char *, char *, unsigned int , unsigned int );
char *ieee_p_3620187407_sub_767668596_3965413181(char *, char *, char *, char *, char *, char *);


static void work_a_0341703375_0521025630_p_0(char *t0)
{
    char t27[16];
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
    char *t28;
    char *t29;

LAB0:    xsi_set_current_line(32, ng0);
    t1 = (t0 + 992U);
    t2 = ieee_p_2592010699_sub_1744673427_503743352(IEEE_P_2592010699, t1, 0U, 0U);
    if (t2 != 0)
        goto LAB2;

LAB4:
LAB3:    t1 = (t0 + 3720);
    *((int *)t1) = 1;

LAB1:    return;
LAB2:    xsi_set_current_line(33, ng0);
    t3 = (t0 + 1192U);
    t4 = *((char **)t3);
    t5 = *((unsigned char *)t4);
    t6 = (t5 == (unsigned char)3);
    if (t6 != 0)
        goto LAB5;

LAB7:    t1 = (t0 + 1352U);
    t3 = *((char **)t1);
    t2 = *((unsigned char *)t3);
    t5 = (t2 == (unsigned char)3);
    if (t5 != 0)
        goto LAB11;

LAB12:    t1 = (t0 + 1512U);
    t3 = *((char **)t1);
    t2 = *((unsigned char *)t3);
    t5 = (t2 == (unsigned char)3);
    if (t5 != 0)
        goto LAB13;

LAB14:
LAB6:    goto LAB3;

LAB5:    xsi_set_current_line(34, ng0);
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
    t16 = (t0 + 3816);
    t23 = (t16 + 56U);
    t24 = *((char **)t23);
    t25 = (t24 + 56U);
    t26 = *((char **)t25);
    memcpy(t26, t3, 16U);
    xsi_driver_first_trans_fast(t16);
    goto LAB6;

LAB8:    t12 = t11;
    goto LAB10;

LAB11:    xsi_set_current_line(36, ng0);
    t1 = (t0 + 1672U);
    t4 = *((char **)t1);
    t1 = (t0 + 3816);
    t7 = (t1 + 56U);
    t8 = *((char **)t7);
    t9 = (t8 + 56U);
    t16 = *((char **)t9);
    memcpy(t16, t4, 16U);
    xsi_driver_first_trans_fast(t1);
    goto LAB6;

LAB13:    xsi_set_current_line(38, ng0);
    t1 = (t0 + 1992U);
    t4 = *((char **)t1);
    t1 = (t0 + 2000U);
    t7 = *((char **)t1);
    t8 = (t0 + 1672U);
    t9 = *((char **)t8);
    t8 = (t0 + 1680U);
    t16 = *((char **)t8);
    t17 = ieee_p_3620187407_sub_767668596_3965413181(IEEE_P_3620187407, t27, t4, t7, t9, t16);
    t23 = (t27 + 12U);
    t12 = *((unsigned int *)t23);
    t14 = (1U * t12);
    t6 = (16U != t14);
    if (t6 == 1)
        goto LAB15;

LAB16:    t24 = (t0 + 3816);
    t25 = (t24 + 56U);
    t26 = *((char **)t25);
    t28 = (t26 + 56U);
    t29 = *((char **)t28);
    memcpy(t29, t17, 16U);
    xsi_driver_first_trans_fast(t24);
    goto LAB6;

LAB15:    xsi_size_not_matching(16U, t14, 0);
    goto LAB16;

}

static void work_a_0341703375_0521025630_p_1(char *t0)
{
    char *t1;
    char *t2;
    char *t3;
    char *t4;
    char *t5;
    char *t6;
    char *t7;

LAB0:    xsi_set_current_line(42, ng0);

LAB3:    t1 = (t0 + 1992U);
    t2 = *((char **)t1);
    t1 = (t0 + 3880);
    t3 = (t1 + 56U);
    t4 = *((char **)t3);
    t5 = (t4 + 56U);
    t6 = *((char **)t5);
    memcpy(t6, t2, 16U);
    xsi_driver_first_trans_fast_port(t1);

LAB2:    t7 = (t0 + 3736);
    *((int *)t7) = 1;

LAB1:    return;
LAB4:    goto LAB2;

}


extern void work_a_0341703375_0521025630_init()
{
	static char *pe[] = {(void *)work_a_0341703375_0521025630_p_0,(void *)work_a_0341703375_0521025630_p_1};
	xsi_register_didat("work_a_0341703375_0521025630", "isim/testCalculator_isim_beh.exe.sim/work/a_0341703375_0521025630.didat");
	xsi_register_executes(pe);
}
