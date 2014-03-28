--------------------------------------------------------------------------
-- TestStack
-- 
-- Name: Bingkun Guo
-- Date: March/2014
-- Email: guobingkun@wustl.edu
-- Assignment: CSE260_SP2014 Lab3
-- File Name: testStack.vhd
--
-- Document my changes:
-- Added a series of unique values that fill 
-- the stack and should then pop items off the stack.
-- Included assertions check that the value on the top of the 
-- stack was correct at all times, and that the full and empty signals 
-- were correct. Checked that a push on a full stack had no effect, and 
-- that a pop on an empty stack had no effect. Checked that the swap 
-- operation worked correctly.
--
library ieee;
use ieee.std_logic_1164.all;
use ieee.numeric_std.all;
use ieee.std_logic_unsigned.all;
use work.txt_util.all;
use work.commonDefs.all;

entity testStack is end testStack; 
architecture a1 of testStack is 
 
component stack 
	generic(
		stakSiz: integer :=  8;
		lgSiz:   integer :=  3;
		wordSiz: integer := 16);
	port(
		clk, reset: in std_logic;	
		push, pop: in std_logic;
		dIn: in std_logic_vector(wordSiz-1 downto 0);
		top: out std_logic_vector(wordSiz-1 downto 0);
		full, empty: out std_logic);
end component;

signal clk, reset : std_logic := '0';
signal push, pop : std_logic := '0';
signal inbits: word := (others => '0');

signal empty, full: std_logic;
signal outbits: word;

-- Clock period definitions
constant clk_period : time := 20 ns;
constant pause: time := 5*clk_period;
 
begin
	uut: stack generic map(8,3,wordSize) 
		   port map(clk,reset,push,pop,inbits,outbits,full,empty);

	process begin
		clk <= '0'; wait for clk_period/2;
		clk <= '1'; wait for clk_period/2;
	end process;
	
	process
	-- reset the stack
	procedure resetSig is begin
		reset <= '1'; wait for pause; reset <= '0'; wait for pause;
	end;
	
	-- push an item into the stack
	procedure pushStack(inval: integer) is begin 
		inbits <= slv(inval,wordSize);
		push <= '1'; wait for clk_period; push <= '0'; wait for pause; 
	end;
	
	-- pop and item from the stack
	procedure popStack is begin 
		pop <= '1'; wait for clk_period; 
		pop <= '0'; wait for pause;
	end;
	
	-- push and pop at the same time
	procedure pushAndPopStack(inval: integer) is begin
		pop <= '1'; push <= '1';
		wait for clk_period;
		pop <= '0'; push <= '0';
		wait for pause;
	end;
	
	begin		
		wait for 100 ns;	
		-- reset stack
		resetSig;
		
		assert empty = '1' report "empty signal low when stack is empty";
		
		-- push until stack is full     
		for i in 1 to 7 loop 
			assert full = '0' report "full signal high when stack is not full";
			pushStack(i); 
			assert int(outbits) = i report "incorrect front value when adding to stack, i=" & str(outbits);
			assert empty = '0' report "empty signal high when stack is not empty";
		end loop;
		
		-- check that full is high
		assert full = '1' report "full signal low when stack is full";
		
		-- attempt to push again
		pushStack(8);
		-- check top value
		assert int(outbits) = 7 report "should be ingnored";
		
		-- and simultaneous push/pop
		pushAndPopStack(8);
		assert int(outbits) = 8 report "incorrect top value " & str(outbits);
		
		popStack;
		assert int(outbits) = 6 report "incorrect top value, should be 6";
		
		-- pop until stack is empty
		for i in 1 to 5 loop
			-- check that stack is not empty
			assert empty = '0' report "empty signal high when queue is not empty";
			-- check that front value is correct
			assert int(outbits) = 7 - i report "at line 106";
			popStack; 
			-- check that queue is not full
			assert full = '0' report "full is high when queue is not full";
		end loop;
		
		assert int(outbits) = 1 report "wrong top value, should be 1";
		popStack;

		assert empty = '1' report "empty signal low when stack is empty";
	
		-- attempt to pop again
		popStack;
		assert empty = '1' report "empty signal low when stack is empty";
		assert full = '0' report "full signal high when stack is not full";
		
		-- attempt to do simultaneous push/pop again
		pushStack(13);
		assert empty = '0' report "empty signal high when stack is not empty";
		assert full = '0' report "full signal high when stack is not full";
		
		assert false report "normal termination" severity failure;
	end process;  
end;
