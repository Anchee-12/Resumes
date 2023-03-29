LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;
USE IEEE.STD_LOGIC_UNSIGNED.ALL;

ENTITY ALU IS
  PORT(S  :IN STD_LOGIC_VECTOR(1 DOWNTO 0);
       A,B  :IN STD_LOGIC_VECTOR(7 DOWNTO 0);
       F  :OUT STD_LOGIC_VECTOR(7 DOWNTO 0));
       
    --   CO,FZ:OUT STD_LOGIC);
       
END ALU;

ARCHITECTURE behave OF ALU IS
  BEGIN
  
  PROCESS(A,B)
    BEGIN
      CASE S IS
        WHEN "00"=> F<=NOT A;
        WHEN "01"=> F<=A+B;
        WHEN "10"=> F<=A-B;
        WHEN "11"=> F<=A+1;
          WHEN OTHERS=> F<="00000000";
      END CASE;
  END PROCESS;
  
END behave;