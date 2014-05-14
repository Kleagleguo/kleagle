------------------------------------------------------------------------------
-- Stack Calculator
-- 
-- Name: Bingkun Guo
-- Date: March/2014
-- Email: guobingkun@wustl.edu
-- Assignment: CSE260_SP2014 Lab3
-- File Name: stackCalc.vhd
--
-- Document the module here
-- 
-- Added component: stack
-- 
-- Added internal signals,
-- push, pop: used to set the push and pop inputs for stack
-- inbits: used to set dIn as inputs for stack
-- outbits: used to connect the output top from stack
-- sum: used to store the previous top value for op5
-- diff: used to store the previous top value for op7
-- empty, full: used to connect the outputs empty and full from stack
-- resetSig: used to set reset signal as inputs for stack
-- state: only used for op5 and op7
-- normalOp: in this state when the operation takes 1 cycle
-- op5_begin: in this state when the operation is 5
-- op7_begin: in this state when the operation is 7 
-- 
-- I have two process blocks, 
-- The first one is synchronous, it is used to specify the inputs
-- for the stack when the operation can complete in 1 cycle
--
-- The second one is asynchronous, it is used to store the sum,diff
-- value in the register for op5,7, and change the state so that 
-- op5,7 can complete in the second cycle.
--
-------------------------------------------------------------------------------
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.std_logic_unsigned.all;
use work.commonDefs.all;

entity stackCalc is 
	generic(
		stakSiz: integer :=  8;
		lgSiz:   integer :=  3;
		wordSiz: integer := 16);
	port(
		clk, reset: in std_logic;	
		op: in nibble;
		doOp: in std_logic;
		dIn: in std_logic_vector(wordSiz-1 downto 0);
		result: out std_logic_vector(wordSiz-1 downto 0));
end stackCalc;

architecture a1 of stackCalc is
component stack is 
	generic(
		stakSiz: integer :=  8;
		lgSiz:   integer :=  3;
		wordSiz: integer := 16);
	port(
		clk, reset: in std_logic;	
		push, pop: in std_logic;
		dIn: in  std_logic_vector(wordSiz-1 downto 0);
		top: out std_logic_vector(wordSiz-1 downto 0);
		empty, full: out std_logic);
end component;

signal push, pop : std_logic;
signal inbits,outbits : std_logic_vector(wordSiz-1 downto 0);
-- sum stores the previous top value for op5
-- diff stores the previous top value for op7
signal sum, diff: std_logic_vector(wordSiz-1 downto 0);
signal empty, full : std_logic;
signal resetSig : std_logic;
type stateType is (normalOp,Op5_begin,Op7_begin);
signal state : stateType; -- indicate the current state for op5,7

begin
	stk: stack generic map(stakSiz,lgSiz,wordSiz) port map(clk,resetSig,push,pop,inbits,outbits,empty,full);
	process(op,doOp,dIn,reset,clk,outbits,empty,full,state) begin
		if reset = '1' then
			push <= '0'; pop <= '0';
			resetSig <= '1';
		elsif doOp = '1' and state = normalOp then
			resetSig <= '0';
			case op is 
				when x"0" => push <= '1'; pop <= '1'; inbits <= (others => '0');
				when x"1" => resetSig <= '1';
				when x"2" => pop <= '1';
				when x"3" => push <= '1'; inbits <= dIn;
				when x"4" => if empty /= '1' then push <= '1'; pop <= '1'; inbits <= (outbits + dIn); end if;
				when x"6" => if empty /= '1' then push <= '1'; pop <= '1'; inbits <= (outbits - dIn); end if;
				when x"5" => pop <= '1';
				when x"7" => pop <= '1';
				when others =>
			end case;
		elsif state = normalOp then
			push <= '0'; pop <= '0';
			resetSig <= '0';
		end if;
		
		case state is
			when Op5_begin =>
				if empty = '1' then 
					pop <= '0'; push <= '1'; inbits <= sum;
				else
					pop <= '1'; push <= '1'; inbits <= sum + outbits;
				end if;
			when Op7_begin => 
				if empty = '1' then
					pop <= '0'; push <= '1'; inbits <= diff;
				else 
					pop <= '1'; push <= '1'; inbits <= diff - outbits;
				end if;
			when others =>
		end case;
	end process;
	
	process(clk) begin
		if rising_edge(clk) then
			if reset = '1' then
				state <= normalOp;
				sum <= (others => '0'); diff <= (others => '0');
			end if;
			
			case state is 
				when normalOp =>
					if op = x"5" and doOp = '1' then
						state <= Op5_begin;
						sum <= outbits;
					elsif op = x"7" and doOp = '1' then
						state <= op7_begin;
						diff <= outbits;
					end if;
				when Op5_begin => state <= normalOp;
				when Op7_begin => state <= normalOp;
				when others =>
			end case;
		end if;
	end process;
	
	result <= outbits;
end a1;
