--------------------------------------------------------------------------
-- Stack circuit
-- 
-- Name: Bingkun Guo
-- Date: March/2014
-- Email: guobingkun@wustl.edu
-- Assignment: CSE260_SP2014 Lab3
-- File Name: stack.vhd
--
-- Document of my changes:
--
-- Added internal signals:
-- stackStore: an array of words which store the elements of the stack
-- topPntr: the top pointer which points to the empty space above the top element
--
-- The entrie process inside a synchronous block,
-- If the reset signal is high, simply reset the top pointer so that
-- it points to index 0.
-- 
-- If the push and pop signals are both high, then simply replace 
-- the top element with dIn.
--
-- If the push signal is high, and the stack is not full, 
-- (i.e.,topPntr < stakSiz-1), we push dIn onto the top and increments
-- top pointer.
--
-- If the pop signal is hgih, and the stack is not empty,
-- (i.e., topPntr > 0), then we pop the top element.
--
-- 
--------------------------------------------------------------------------

library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.std_logic_unsigned.all;
use work.commonDefs.all;

entity stack is 
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
end stack;

architecture a1 of stack is

type stackStoreTyp is array(0 to stakSiz-2) of std_logic_vector(wordSiz-1 downto 0);
signal stackStore : stackStoreTyp; -- an array of words which store stack elements
signal topPntr: std_logic_vector(lgSiz-1 downto 0); -- point to the empty space above top

begin
	process (clk) begin
		if rising_edge(clk) then
			if reset = '1' then
				topPntr <= (others => '0'); 
			else 
				if pop = '1' and push = '1' then -- swap operation
					if topPntr /= 0 then
						stackStore(int(topPntr-1)) <= dIn;
					end if;
				elsif push = '1' and topPntr < stakSiz-1 then -- push when not full
					stackStore(int(topPntr)) <= dIn;
					topPntr <= topPntr + 1;
				elsif pop = '1' and topPntr > 0 then -- pop when not empty
					topPntr <= topPntr - 1;
				end if;
			end if;
		end if;
	end process;
	-- return the last element in the array as the top.
	-- if the stack is empty, simply return stackStore(0)
	top <= stackStore(int(topPntr-1)) when topPntr > 0 else stackStore(0);
	-- empty is high when the top pointer points to 0
	empty <= '1' when topPntr = 0 else '0';
	-- full is high when the top pointer = stackSize - 1
	full <= '1' when topPntr = (stakSiz-1) else '0';
end a1;
