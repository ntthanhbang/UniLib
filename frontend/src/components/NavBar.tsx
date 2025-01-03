import React from 'react';
import LogoutButton from './LogoutButton';

const Navbar: React.FC = () => {
    return (
        <nav className="bg-blue-500 p-4">
          <div className="container mx-auto flex justify-between items-center"> {/* Use flexbox for layout */}
            <h1 className="text-white text-2xl font-bold">UniLib</h1>
            <LogoutButton /> {/*logout */}
          </div>
        </nav>
      );
    };

export default Navbar;