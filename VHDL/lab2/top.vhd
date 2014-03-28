---------------------------------------------------------------------
-- Top module for pattern matcher
--
-- Name: Bingkun Guo
-- Date: Feb/2014
-- Email: guobingkun@wustl.edu
-- Assignment: CSE260_SP2014 Lab1
-- File Name: top.vhd
--
-- Document of my changes:
--
-- I added 3 components which are patternMatcher, binaryInMod
-- outMod.
--
-- Then I defined several internal signals which would be used
-- to connect these 3 modules.
--
-- 
---------------------------------------------------------------------
library IEEE;
use ieee.std_logic_1164.all;
use IEEE.numeric_std.all;
use IEEE.std_logic_unsigned.all;
use work.commonDefs.all;

entity top is port(
	clk: in std_logic;
	-- S3 board buttons, knob, switches and LEDs
	btn: in buttons;
	knob: in knobSigs;
	swt: in switches;
	led: out leds;
	-- signals for controlling LCD display 
	lcd: out lcdSigs);
end top;

architecture a1 of top is

component patternMatcher is port(
	clk, restart, valid: in std_logic;
	inSym: in nibble;
	repCount: in nibble;
	patCount: out byte);
end component patternMatcher;

component binaryInMod is port(
	clk: in std_logic;
	btn: in buttons;
	knob: in knobSigs;
	resetOut: out std_logic;
	dBtn: out std_logic_vector(3 downto 1);
	pulse: out std_logic_vector(3 downto 1);
	inBits: out word);
end component binaryInMod;

component outMod is port(
	clk, reset: in std_logic;
	inSym: in nibble;					-- input symbol to matcher
	valid: in std_logic;				-- valid symbol signal
	patCount: in byte;				-- number of matches since restart
	-- signals for controlling LCD display 
	lcd: out lcdSigs);
end component outMod;

signal restart, valid : std_logic;
signal inSym, repCount : nibble;
signal patCount : byte;
signal dBtn, pulse : std_logic_vector(3 downto 1);
signal inBits : word;	   -- output of binaryInMod
signal outBits : byte;
signal reset : std_logic;	-- corresponds to reset button

begin

	imod: binaryInMod port map(clk,btn,knob,reset,dBtn,pulse,inBits);
	pmatch: patternMatcher port map(clk,restart,valid,inSym,repCount,outBits);
	omod: outMod port map(clk,reset,inSym,valid,outBits,lcd);
	
	restart <= pulse(1);  -- corresponds to button 1
	valid <= pulse(2);	 -- corresponds to button 2
	inSym <= inBits(3 downto 0);
	repCount <= inBits(11 downto 8);
	
	led(3 downto 0) <= inSym;
	led(7 downto 4) <= repCount;
	
end a1;
